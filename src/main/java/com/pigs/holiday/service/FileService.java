package com.pigs.holiday.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import com.pigs.holiday.config.S3Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final S3Config s3Config;
    private final AmazonS3Client amazonS3Client;

    public String uploadFile(MultipartFile file, String dirName) throws IOException {

        //뽑아낸 이미지 파일에서 이름 및 확장자 추출
        String fileName = file.getOriginalFilename();
        String fileExtension = fileName.substring(fileName.indexOf("."));
        String uuidFileName = dirName + UUID.randomUUID() + fileExtension;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        s3Config.amazonS3Client().putObject(
                new PutObjectRequest(bucket, uuidFileName, file.getInputStream(), metadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );

        String s3Url = s3Config.amazonS3Client().getUrl(bucket, uuidFileName).toString();


        return s3Url;
    }


    public List<String> uploadFiles(List<MultipartFile> files, String dirName) throws IOException {
        List<String> s3Urls = new ArrayList<>();

        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    s3Urls.add(uploadFile(file, dirName));
                }
            }
        }
        return s3Urls;
    }


}
