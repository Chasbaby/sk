package com.ruoyi.common.utils.file;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.file.FileNameLengthLimitExceededException;
import com.ruoyi.common.exception.file.FileSizeLimitExceededException;
import com.ruoyi.common.exception.file.InvalidExtensionException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;

/**
 * 文件上传工具类
 *
 * @author ruoyi
 */
public class FileUploadUtils
{
    /**
     * 默认大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 50 * 1024 * 1024;

    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    /**
     * 默认上传的地址
     */
    private static String defaultBaseDir = RuoYiConfig.getProfile();

    public static void setDefaultBaseDir(String defaultBaseDir) {
        FileUploadUtils.defaultBaseDir = defaultBaseDir;
    }

    public static String getDefaultBaseDir() {
        return defaultBaseDir;
    }

    /**
     * 以 默认 配置进行文件上传
     *
     * @param file 上传的文件
     * @return 文件名称
     * @throws Exception
     */
    public static final String upload(MultipartFile file) throws IOException {
        try
        {
            // 文件上传          上传默认基地址     文件      复杂类型工具类  可以上传的 后缀名
            return upload(getDefaultBaseDir(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        }
        catch (Exception e)
        {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String upload(String baseDir, MultipartFile file) throws IOException {
        try
        {
            //           给定基地址上传
            return upload(baseDir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        }
        catch (Exception e)
        {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 文件上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @param allowedExtension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws FileSizeLimitExceededException 如果超出最大大小
     * @throws FileNameLengthLimitExceededException 文件名太长
     * @throws IOException 比如读写文件出错时
     * @throws InvalidExtensionException 文件校验异常
     */
    public static final String upload(String baseDir, MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException,
            InvalidExtensionException
    {
        //获得 文件名 的 长度
        int fileNamelength = file.getOriginalFilename().length();
        // 名字长度 不能大于 100
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH)
        {
            throw new FileNameLengthLimitExceededException(FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }

        // 文件大小校验   上传的文件    上传文件的类型
        assertAllowed(file, allowedExtension);

        //编码文件名
        String fileName = extractFilename(file);
        //获得该地址的绝对文件
        File desc = getAbsoluteFile(baseDir, fileName);
        //这个方法必须填写绝对路径  且 必须存在 否则报错
        file.transferTo(desc);
        //获得路径文件名
        String pathFileName = getPathFileName(baseDir, fileName);
        //返回路径文件名
        return pathFileName;
    }

    /**
     * 编码文件名  (也就是 用 uuid 改个名字)
     */
    public static final String extractFilename(MultipartFile file) {

        String fileName = file.getOriginalFilename();
        //获得文件的 后缀
        String extension = getExtension(file);
        //获得日期路径 + uuid + 文件名
        fileName = DateUtils.datePath() + "/" + IdUtils.fastUUID() + "." + extension;
        //返回文件名(路径)
        return fileName;
    }

    // 获得绝对文件
    public static final File getAbsoluteFile(String uploadDir, String fileName) throws IOException {
        //                            上传文件    获得当前系统文件分隔符  文件名
        File desc = new File(uploadDir + File.separator + fileName);

        // 如果 文件不存在
        if (!desc.exists())
        {
            // 如果文件 的父文件不存在
            if (!desc.getParentFile().exists())
            {
                // 则 创建 父文件 路径
                desc.getParentFile().mkdirs();
            }
        }
        // 然后返回文件的绝对路径
        return desc;
    }

    //获得路径文件名
    public static final String getPathFileName(String uploadDir, String fileName) throws IOException {
        //长度
        int dirLastIndex = RuoYiConfig.getProfile().length() + 1;
        // 当前目录   拼接
        String currentDir = StringUtils.substring(uploadDir, dirLastIndex);
        //资源映射路径 前缀
        String pathFileName = Constants.RESOURCE_PREFIX + "/" + currentDir + "/" + fileName;
        //返回路径文件名
        return pathFileName;
    }

    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     * @return
     * @throws FileSizeLimitExceededException 如果超出最大大小
     * @throws InvalidExtensionException
     */
    public static final void assertAllowed(MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, InvalidExtensionException
    {
        // 文件大小
        long size = file.getSize();
        if (DEFAULT_MAX_SIZE != -1 && size > DEFAULT_MAX_SIZE)
        {
            throw new FileSizeLimitExceededException(DEFAULT_MAX_SIZE / 1024 / 1024);
        }

        // 获得上传时的文件名
        String fileName = file.getOriginalFilename();
        //得到文件 后缀名
        String extension = getExtension(file);
        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension))
        {
            if (allowedExtension == MimeTypeUtils.IMAGE_EXTENSION)
            {
                throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension,
                        fileName);
            }
            else if (allowedExtension == MimeTypeUtils.FLASH_EXTENSION)
            {
                throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension,
                        fileName);
            }
            else if (allowedExtension == MimeTypeUtils.MEDIA_EXTENSION)
            {
                throw new InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension,
                        fileName);
            }
            else if (allowedExtension == MimeTypeUtils.VIDEO_EXTENSION)
            {
                throw new InvalidExtensionException.InvalidVideoExtensionException(allowedExtension, extension,
                        fileName);
            }
            else
            {
                throw new InvalidExtensionException(allowedExtension, extension, fileName);
            }
        }

    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param extension
     * @param allowedExtension
     * @return
     */
    public static final boolean isAllowedExtension(String extension, String[] allowedExtension) {
        for (String str : allowedExtension)
        {
            if (str.equalsIgnoreCase(extension))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件名的后缀
     *
     * @param file 表单文件
     * @return 后缀名
     */
    public static final String getExtension(MultipartFile file) {
        //获得 上传时的文件 后缀
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        // 后缀如果是空的
        if (StringUtils.isEmpty(extension))
        {
            //获得内容类型     eg: image/jpeg  text/html
            extension = MimeTypeUtils.getExtension(file.getContentType());
        }
        return extension;
    }
}
