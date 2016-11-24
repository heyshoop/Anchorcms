package com.anchorcms.cms.controller.admin;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.anchorcms.common.image.ImageScale;
import com.anchorcms.common.upload.FileRepository;
import com.anchorcms.common.utils.CmsUtils;
import com.anchorcms.core.model.CmsSite;
import com.anchorcms.core.model.Ftp;
import com.anchorcms.core.service.DbFileService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @Author 阁楼麻雀
 * @Date 2016/11/24 17:22
 * @Desc 图片裁剪
 */

@Controller
public class ImageCutController {
	private static final Logger log = LoggerFactory
			.getLogger(ImageCutController.class);
	/**
	 * 图片选择页面
	 */
	public static final String IMAGE_SELECT_RESULT = "/common/image_area_select";
	/**
	 * 图片裁剪完成页面
	 */
	public static final String IMAGE_CUTED = "/common/image_cuted";
	/**
	 * 错误信息参数
	 */
	public static final String ERROR = "error";

	@RequiresPermissions("common:v_image_area_select")
	@RequestMapping("/common/v_image_area_select.do")
	public String imageAreaSelect(String uploadBase, String imgSrcPath,
			Integer zoomWidth, Integer zoomHeight, String uploadNum,
			HttpServletRequest request, ModelMap model) {
		model.addAttribute("uploadBase", uploadBase);
		model.addAttribute("imgSrcPath", imgSrcPath);
		model.addAttribute("zoomWidth", zoomWidth);
		model.addAttribute("zoomHeight", zoomHeight);
		model.addAttribute("uploadNum", uploadNum);
		return IMAGE_SELECT_RESULT;
	}

	@RequiresPermissions("common:o_image_cut")
	@RequestMapping("/common/o_image_cut.do")
	public String imageCut(String imgSrcPath, Integer imgTop, Integer imgLeft,
			Integer imgWidth, Integer imgHeight, Integer reMinWidth,
			Integer reMinHeight, Float imgScale, String uploadNum,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		try {
			if (imgWidth > 0) {
				if (site.getConfig().getIsUploadToDb()) {
					String dbFilePath = site.getConfig().getDbFileUri();
					imgSrcPath = imgSrcPath.substring(dbFilePath.length());
					File file = dbFileService.retrieve(imgSrcPath);
					imageScale.resizeFix(file, file, reMinWidth, reMinHeight,
							getLen(imgTop, imgScale),
							getLen(imgLeft, imgScale), getLen(imgWidth,
									imgScale), getLen(imgHeight, imgScale));
					dbFileService.restore(imgSrcPath, file);
				} else if (site.getUploadFtp() != null) {
					Ftp ftp = site.getUploadFtp();
					String ftpUrl = ftp.getUrl();
					imgSrcPath = imgSrcPath.substring(ftpUrl.length());
					String fileName=imgSrcPath.substring(imgSrcPath.lastIndexOf("/"));
					File file = ftp.retrieve(imgSrcPath,fileName);
					imageScale.resizeFix(file, file, reMinWidth, reMinHeight,
							getLen(imgTop, imgScale),
							getLen(imgLeft, imgScale), getLen(imgWidth,
									imgScale), getLen(imgHeight, imgScale));
					ftp.restore(imgSrcPath, file);
				} else {
					String ctx = request.getContextPath();
					imgSrcPath = imgSrcPath.substring(ctx.length());
					File file = fileRepository.retrieve(imgSrcPath);
					imageScale.resizeFix(file, file, reMinWidth, reMinHeight,
							getLen(imgTop, imgScale),
							getLen(imgLeft, imgScale), getLen(imgWidth,
									imgScale), getLen(imgHeight, imgScale));
				}
			} else {
				if (site.getConfig().getIsUploadToDb()) {
					String dbFilePath = site.getConfig().getDbFileUri();
					imgSrcPath = imgSrcPath.substring(dbFilePath.length());
					File file = dbFileService.retrieve(imgSrcPath);
					imageScale.resizeFix(file, file, reMinWidth, reMinHeight);
					dbFileService.restore(imgSrcPath, file);
				} else if (site.getUploadFtp() != null) {
					Ftp ftp = site.getUploadFtp();
					String ftpUrl = ftp.getUrl();
					imgSrcPath = imgSrcPath.substring(ftpUrl.length());
					String fileName=imgSrcPath.substring(imgSrcPath.lastIndexOf("/"));
					File file = ftp.retrieve(imgSrcPath,fileName);
					imageScale.resizeFix(file, file, reMinWidth, reMinHeight);
					ftp.restore(imgSrcPath, file);
				} else {
					String ctx = request.getContextPath();
					imgSrcPath = imgSrcPath.substring(ctx.length());
					File file = fileRepository.retrieve(imgSrcPath);
					imageScale.resizeFix(file, file, reMinWidth, reMinHeight);
				}
			}
			model.addAttribute("uploadNum", uploadNum);
		} catch (Exception e) {
			log.error("cut image error", e);
			model.addAttribute(ERROR, e.getMessage());
		}
		return IMAGE_CUTED;
	}

	private int getLen(int len, float imgScale) {
		return Math.round(len / imgScale);
	}

	@Resource
	private ImageScale imageScale;
	@Resource
	private FileRepository fileRepository;
	@Resource
	private DbFileService dbFileService;

}
