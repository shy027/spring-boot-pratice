package top.shy.springboot.filter_interceptor.utils;

import cn.hutool.core.img.ImgUtil;
import com.aliyun.oss.OSS;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.shy.springboot.filter_interceptor.config.OssConfig;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.UUID;

@Component
public class OssUtil {
    @Resource
    private OSS ossClient;

    @Resource
    private OssConfig ossConfig;

    public String uploadFile(MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null) {
                throw new IllegalArgumentException("文件名称不能为空");
            }

            // 生成唯一文件名，防止覆盖
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String fileName = UUID.randomUUID() + "_" + originalFilename;

            // 添加水印
            Image originalImage = ImageIO.read(file.getInputStream());
            Image watermarkedImage = ImgUtil.pressText(originalImage, "水印", Color.WHITE,
                    new Font("黑体", Font.BOLD, 60), 10, 20, 0.5f);

            // 将带水印的图片转换为BufferedImage
            BufferedImage watermarkedBufferedImage = new BufferedImage(
                    watermarkedImage.getWidth(null),
                    watermarkedImage.getHeight(null),
                    BufferedImage.TYPE_INT_RGB
            );
            Graphics2D g = watermarkedBufferedImage.createGraphics();
            g.drawImage(watermarkedImage, 0, 0, null);
            g.dispose();

            // 将带水印的图片写入输出流
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(watermarkedBufferedImage, fileExtension, outputStream);
            InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

            // 上传文件到OSS
            try {
                ossClient.putObject(ossConfig.getBucketName(), fileName, inputStream);
                System.out.println("File uploaded successfully: " + fileName);
            } catch (Exception e) {
                System.err.println("Error uploading file: " + e.getMessage());
                throw new RuntimeException("文件上传失败: " + e.getMessage(), e);
            }

            // 构建文件的URL路径
            return "https://" + ossConfig.getBucketName() + "." +
                    ossConfig.getEndpoint().replace("https://", "") + "/" + fileName;
        } catch (Exception e) {
            throw new RuntimeException("文件上传失败: " + e.getMessage(), e);
        }
    }
}
