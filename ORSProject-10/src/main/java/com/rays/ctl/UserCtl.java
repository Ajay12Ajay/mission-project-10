package com.rays.ctl;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.AttachmentDTO;
import com.rays.dto.RoleDTO;
import com.rays.dto.UserDTO;
import com.rays.form.UserForm;
import com.rays.service.AttachmentServiceInt;
import com.rays.service.RoleServiceInt;
import com.rays.service.UserServiceInt;

@RestController
@RequestMapping(value = "User")
public class UserCtl extends BaseCtl<UserDTO, UserForm, UserServiceInt> {

	@Autowired
	RoleServiceInt roleService;
	
	@Autowired
	AttachmentServiceInt attachmentService;

	@GetMapping("preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);

		RoleDTO dto = new RoleDTO();

		List<DropdownList> roleList = roleService.search(dto, userContext);

		res.addResult("roleList", roleList);

		return res;
	}

	@PostMapping("/profilePic/{userId}")
	public ORSResponse uploadPic(@PathVariable Long userId, @RequestParam("file") MultipartFile file,
			HttpServletRequest req) {

		AttachmentDTO attachmentDto = new AttachmentDTO(file);

		attachmentDto.setDescription("profile pic");

		attachmentDto.setUserId(userId);

		UserDTO userDto = service.findById(userId, userContext);

		if (userDto.getImageId() != null && userDto.getImageId() > 0) {

			attachmentDto.setId(userDto.getImageId());

		}

		Long imageId = attachmentService.save(attachmentDto, userContext);

		if (userDto.getImageId() == null) {

			userDto.setImageId(imageId);

			service.update(userDto, userContext);
		}

		ORSResponse res = new ORSResponse();

		res.addResult("imageId", imageId);
		res.setSuccess(true);

		return res;
	}

	@GetMapping("/profilePic/{userId}")
	public void downloadPic(@PathVariable Long userId, HttpServletResponse response) {

		try {

			UserDTO userDto = service.findById(userId, userContext);

			AttachmentDTO attachmentDTO = null;

			if (userDto != null) {
				attachmentDTO = attachmentService.findById(userDto.getImageId(), userContext);
			}

			if (attachmentDTO != null) {
				response.setContentType(attachmentDTO.getType());
				OutputStream out = response.getOutputStream();
				out.write(attachmentDTO.getDoc());
				out.close();
			} else {
				response.getWriter().write("ERROR: File not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
