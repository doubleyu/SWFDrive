package com.dby.redshift.cluster;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.IpPermission;
import com.amazonaws.services.redshift.AmazonRedshift;
import com.amazonaws.services.redshift.AmazonRedshiftClient;
import com.amazonaws.services.redshift.model.Cluster;
import com.amazonaws.services.redshift.model.ClusterAlreadyExistsException;
import com.amazonaws.services.redshift.model.CreateClusterRequest;
import com.amazonaws.services.redshift.model.DescribeClustersRequest;
import com.amazonaws.services.redshift.model.DescribeClustersResult;
import com.swf.common.ConfigHelper;


public class RedshiftCluster {
    private static String TEMP_ACCESS_KEY_ID = null;
    private static String TEMP_SECRET_ACCESS_KEY = null;
    
    private ConfigHelper configHelper;

    public static AmazonRedshift client;
    public static AmazonEC2 ec2;
    public static String clusterIdentifier = "redshift-cluster3";
    public static long sleepTime = 20;

    @Deprecated
    private RedshiftCluster(){
        super();

//        TEMP_ACCESS_KEY_ID = PropEditor
//                .getProp(PublicStr.TEMP_ACCESS_KEY_ID);
//        TEMP_SECRET_ACCESS_KEY = PropEditor
//                .getProp(PublicStr.TEMP_SECRET_ACCESS_KEY);
//
//        System.out.println(TEMP_ACCESS_KEY_ID + "----" + TEMP_SECRET_ACCESS_KEY);
//        AWSCredentials credentials = new BasicAWSCredentials(TEMP_ACCESS_KEY_ID,
//                TEMP_SECRET_ACCESS_KEY);
//
//        client = new AmazonRedshiftClient(credentials);
//        client.setEndpoint("https://redshift.us-west-2.amazonaws.com/");
//        ec2 = new AmazonEC2Client(credentials);
//        ec2.setRegion(Region.getRegion(Regions.US_WEST_2));
    }

    public RedshiftCluster(AWSCredentials credentials){
        client = new AmazonRedshiftClient(credentials);
//        client.setEndpoint("https://redshift.us-west-2.amazonaws.com/");
        ec2 = new AmazonEC2Client(credentials);
//        ec2.setRegion(Region.getRegion(Regions.US_WEST_2));
    }
    
    public RedshiftCluster(ConfigHelper configHelper){
    	this.configHelper = configHelper;
    	client = configHelper.createRedshiftClient();
    	ec2 = new AmazonEC2Client(configHelper.createRedshiftCredentials());
    }

    public void createCluster(){

        try {
            createRedshiftCluster();
            waitForClusterReady();
        } catch (ClusterAlreadyExistsException e) {
            System.out.println("Cluster Already Exists");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void createRedshiftCluster() {
        CreateClusterRequest request = new CreateClusterRequest()
                .withClusterIdentifier(clusterIdentifier)
                .withMasterUsername("masteruser")
                .withMasterUserPassword("Double123").withNodeType("ds1.xlarge")
                .withNumberOfNodes(2);

        Cluster createResponse = client.createCluster(request);
        System.out.println("Created cluster "
                + createResponse.getClusterIdentifier());
    }

    private void waitForClusterReady() throws InterruptedException {
        Cluster cluster = null;
        Boolean clusterReady = false;
        System.out.println("Wating for cluster to become available.");
        while (!clusterReady) {
            DescribeClustersResult result = client
                    .describeClusters(new DescribeClustersRequest()
                            .withClusterIdentifier(clusterIdentifier));
            cluster = result.getClusters().get(0);



            String status = (result.getClusters()).get(0).getClusterStatus();
            if (status.equalsIgnoreCase("available")) {
                clusterReady = true;
                String address = cluster.getEndpoint().getAddress();
                String port = cluster.getEndpoint().getPort().toString();
                System.out.println(address+"  :  "+port);
                
                configHelper.setRedshiftJdbcUrl("jdbc:redshift://"+address+":"+port+"/dev");
                
//                PropEditor.setProp(PublicStr.JDBC_URL, "jdbc:redshift://"+address+":"+port+"/dev");
//
//                System.out.println(PropEditor.getProp(PublicStr.JDBC_URL));



//		        CreateSecurityGroupRequest csgr = new CreateSecurityGroupRequest();
//
//		        csgr.withGroupName("JavaSecurityGroup-lyy").withDescription("My security group");

//		        CreateSecurityGroupResult createSecurityGroupResult =
//		        	    ec2.createSecurityGroup(csgr);

                IpPermission ipPermission =
                        new IpPermission();

                ipPermission.withIpRanges("0.0.0.0/0")
                        .withIpProtocol("-1")
                        .withFromPort(-1)
                        .withToPort(-1);

                AuthorizeSecurityGroupIngressRequest authorizeSecurityGroupIngressRequest =
                        new AuthorizeSecurityGroupIngressRequest().withGroupName("default")
                                .withIpPermissions(ipPermission);

                ec2.authorizeSecurityGroupIngress(authorizeSecurityGroupIngressRequest);
            } else {
                System.out.print(".");
                Thread.sleep(sleepTime * 1000);
            }
        }
    }
}