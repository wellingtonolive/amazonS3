package com.amazon.s3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import java.io.File;
import java.util.List;

public class S3 {


    public static void main(String[] args) {

        String acessKey = "AKIA3H2YJ7JWS5DXMMH2";
        String secretKey = "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";

        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(acessKey, secretKey);

        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.SA_EAST_1)
                .build();

        System.out.printf("Criando Buckets");
        String bucketName = "wellingtonnovo";
        s3.createBucket(bucketName);


        System.out.println("Lista de Buckets...");
        List<Bucket> buckets = s3.listBuckets();
        buckets.forEach(item -> {
            System.out.println(item.getName());
        });

        System.out.printf("UpLoad de Arquivo");
        String diretorioArquivo = "";
        File file = new File(diretorioArquivo);
        s3.putObject(bucketName, "name-destino", file);

        System.out.printf("Listando Objetos");
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(bucketName);
        ObjectListing listObjects = s3.listObjects(listObjectsRequest);
        for (S3ObjectSummary objetSummary : listObjects.getObjectSummaries()) {
            System.out.println("*" + objetSummary.getKey() + " - "+ objetSummary.getSize());
        }

        System.out.printf("Apagando Arquivos do Bucket");
        s3.deleteObject(bucketName,"keyOfObject");


        System.out.printf("Apagando Bucket Of S3");
        s3.deleteBucket(bucketName);

    }
}
