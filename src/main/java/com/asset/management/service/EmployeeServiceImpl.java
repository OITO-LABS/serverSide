
package com.asset.management.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import com.asset.management.VO.AssetVO;
import com.asset.management.VO.EmployeeVo;
import com.asset.management.VO.PaginationVO;
import com.asset.management.dao.EmployeeDao;
import com.asset.management.dao.entity.Employee;

@Component
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao daoobj;
	
	@Override
	public List<EmployeeVo> selectAll() {
		return daoobj.selectAll();

	}

	@Override
	public Long register(EmployeeVo employee) throws Exception {
		return daoobj.register(employee);
	}

	@Override
	public void delete(Long id) {
		daoobj.delete(id);

	}

	@Override
	public EmployeeVo view(Long id) {
		return daoobj.view(id);
	}

	@Override
	public void update(Long id, EmployeeVo obj) throws Exception {
		daoobj.update(id, obj);
	}

	@Override
	public void remove(String id) throws Exception {
		daoobj.remove(id);
	}

	@Override
	public Page<Employee> page(PaginationVO pagination) {
		return daoobj.page(pagination);
	}

	@Override
	public List<AssetVO> getAsset(Long id) {
		return daoobj.getAsset(id);
	}

	@Override
	public Page<Employee> searchEmployee(PaginationVO pagination) {
		return daoobj.searchEmployee(pagination);
	}

	@Override
	public List<String> disable(Long login) {
		return daoobj.disable(login);
	}

	@Override
	public void upload(MultipartFile image,Long id) throws Exception {
		byte[] bytes = image.getBytes();
		Path path = Paths.get(Paths.get(".") + "/src/main/resources/public/photos/" + id +".jpg");
		Files.write(path, bytes);
	}

	@Override
	public void viewImage(HttpServletResponse response,Long empId) throws Exception {
		 var imgFile = new ClassPathResource("public/photos/"+ empId + ".jpg");
	        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
	}

	
	

}