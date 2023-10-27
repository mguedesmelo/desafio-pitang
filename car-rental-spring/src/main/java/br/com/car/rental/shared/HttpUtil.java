package br.com.car.rental.shared;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.car.rental.exception.ResponseMessage;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class HttpUtil {
    @Autowired
    private ObjectMapper objectMapper;

	private List<String> freePatterns = List.of(
			"/api/signin", 
			"/api/logout", 
			"/api/users",
			"/h2-console",
			"/css", 
			"/icons", 
			"/images",
			"/js", 
			"/plugins", 
			"/image");

    public RequestMatcher[] getFreeRequestMatchers() {
    	return (RequestMatcher[]) this.freePatterns.stream().map(pattern -> {
    		return AntPathRequestMatcher.antMatcher(pattern);
        }).collect(Collectors.toList()).toArray();
    }

    public boolean isFreeToNavigate(String pattern) {
		return this.freePatterns.stream().filter(p -> pattern.startsWith(p)).count() > 0;
    }
    
	public void sendResponseMessage(HttpServletResponse response, int responseCode, 
			String responseBody) throws IOException {
	    response.addHeader("Content-Type", "application/json;charset=UTF-8");
	    response.setStatus(responseCode);
	    ResponseMessage unauthorized = new ResponseMessage(responseCode, responseBody);
	    objectMapper.writeValue(response.getOutputStream(), unauthorized);
	    response.flushBuffer();			
	}
}
