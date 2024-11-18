package top.shy.springboot.file.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.shy.springboot.file.config.OssConfig;

import java.io.IOException;
import java.io.InputStream;

@Component
public class OssTemplate {

    @Resource
    private OssConfig ossConfig;

    /**
     * 上传文件到阿里云OSS
     *
     * @param file 要上传的文件
     * @return 上传后的文件URL
     */
    public String ossUpload(MultipartFile file) {
        // 检查文件是否为空
        if (file.isEmpty()) {
            throw new RuntimeException("文件不能为空");
        }

        // 创建OSSClient实例
        OSS ossClient = null;
        try {
            ossClient = new OSSClientBuilder().build(ossConfig.getEndpoint(), ossConfig.getAk(), ossConfig.getSecret());
            // 上传文件
            String fileUrl = uploadFile(ossClient, file);
            return fileUrl;
        } catch (Exception e) {
            throw new RuntimeException("上传文件失败", e);
        } finally {
            // 关闭OSSClient
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
    private String uploadFile(OSS ossClient, MultipartFile file) {
        // 文件名
        String fileName = file.getOriginalFilename();
        // 上传到OSS的路径
        String key = ossConfig.getDir() + "/" + fileName;

        try {
            // 获取文件的输入流
            InputStream inputStream = file.getInputStream();
            // 上传文件
            ossClient.putObject(ossConfig.getBucket(), key, inputStream);
            // 关闭输入流
            inputStream.close();
            // 返回文件URL
            return ossConfig.getHost() + "/" + key;
        } catch (IOException e) {
            throw new RuntimeException("上传文件失败", e);
        }
    }

}
