package kr.sir.service.install.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import kr.sir.domain.module.ConfigForm;
import kr.sir.domain.repository.install.InstallEmRepository;
import kr.sir.service.install.InstallService;

@Service
public class InstallServiceImpl implements InstallService {

	private InstallEmRepository installEmRepository;

	@Autowired
	public void setInstallEmRepository(InstallEmRepository installEmRepository) {
		this.installEmRepository = installEmRepository;
	}

	// table에 설정 정보 insert
	@Override
	public int writeConfigInfo(String prefix, ConfigForm configForm) {
		return installEmRepository.writeConfigInfo(prefix, configForm);
	}

	// 전체 table 생성
	@Override
	public void createTable(ClassPathResource classPathResource, String prefix) {
		installEmRepository.createTable(classPathResource, prefix);
	}

	// config.yml에 table prefix 정보 저장하기
	@Override
	public void writeConfigToYaml(String prefix) throws FileNotFoundException, IOException {
		Yaml yaml = new Yaml();
		String path = getClassPathResource("application.yml");	// application.yml과 같은 경로 얻기
		
		System.out.println("path : " + path);
		System.out.println("prefix : " + prefix);
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("prefix", prefix);
		File file = new File(path + "config.yml");	// 얻은 경로에 파일 생성
		String output = dumpYamlData(yaml, data);
		System.out.println("yaml output : " + output);
		writeToFileByBufferedWriter(output, file);
		System.out.println("write into config.yml success!");
	}


	private String dumpYamlData(Yaml yaml, Map<String, Object> data) {
		return yaml.dump(data);	// yaml 형식으로 data 저장
	}

	private String getClassPathResource(String string) {
		return "src/main/resources/";
//		return new ClassPathResource("application.yml").getPath();
	}
	
	private void writeToFileByBufferedWriter(String output, File file) throws IOException {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file));
			// write into file
			bw.write(output);
			bw.flush();
		} finally {
			closeBufferedWriter(bw);
		}
	}

	private void closeBufferedWriter(BufferedWriter bw) throws IOException {
		if(bw!=null) {
			bw.close();
		}
	}
	
	

}
