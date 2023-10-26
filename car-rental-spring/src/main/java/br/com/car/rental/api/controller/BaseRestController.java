package br.com.car.rental.api.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ResourceUtils;

import br.com.car.rental.model.BaseEntity;
import br.com.car.rental.model.User;
import br.com.car.rental.repository.UserRepository;
import br.com.car.rental.shared.StringUtil;
import jakarta.servlet.http.HttpServletResponse;

public abstract class BaseRestController<T extends BaseEntity> {
	@Autowired
	private UserRepository userRepository;

	protected void loadImage(HttpServletResponse response, String imageContentType, byte[] image) {
		try {
			if (StringUtil.isNullOrEmpty(imageContentType)) {
				loadNoPhoto(response);
			} else {
				response.setContentType(imageContentType);
				IOUtils.copy(new ByteArrayInputStream(image), response.getOutputStream());
			}
		} catch (IOException e) {
			// Empty
		}
	}

	protected void loadNoPhoto(HttpServletResponse response) {
		try {
			response.setContentType("image/png");
			File file = ResourceUtils.getFile("classpath:static/image/nophoto80.png");
			IOUtils.copy(new FileInputStream(file), response.getOutputStream());
		} catch (IOException e) {
			// Empty
		}
	}
	
	protected User getLoggedUser() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        if (login != null) {
            return this.userRepository.findByLogin(login).orElse(null);
        }
        return null;
    }
}
