package com.amazon.s3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

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

        List<Bucket> buckets = s3.listBuckets();

        System.out.println("Lista de Buckets...");
        buckets.forEach(item -> {
            System.out.println(item.getName());
        });




    }
}
