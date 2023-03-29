package com.toyoucafe.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {
	
	public static final String FILE_UPLOAD_PATH = "D:\\이영섭\\springProject\\upload\\images";
	
	private static Logger logger = LoggerFactory.getLogger(FileManagerService.class);
	
	public static String saveFile(int userId, MultipartFile file) {
	
		// 파일 저장
		
		// 사용자 별로 폴더를 구분한다. 
		// 사용자 별로 폴더를 새로 만든다
		// 폴더이름 : userId_현재시간
		// UNIX TIME : 1970년 1월 1일 부터 흐른 시간 (millisecond 1/1000)
		// D:\\이영섭\\springProject\\upload\\images
		
	
		String directoryName = "/" + userId + "_" + System.currentTimeMillis() + "/";
		
		// 디렉토리 생성
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		File directory = new File(directoryPath);
		
		if(!directory.mkdir()) {
			// 디렉토리 생성 실패
			
			logger.error("saveFile : 디렉토르 생성 실패 " + directoryPath);
			return null;
		}
		
		// 파일 저장
		try {
		
			byte[] bytes = file.getBytes();
			
			String filePath = directoryPath + file.getOriginalFilename();
			Path path = Paths.get(filePath);
			Files.write(path, bytes);
			
		} catch (IOException e) {
			
			
			logger.error("saveFile : 디렉토르 저장 실패 " + directoryPath);
			e.printStackTrace();
			
			return null;
		}
		
		// 클라이언트에 접근 가능한 경로를 문자열로 리턴
		
		// http://localhost:8090/images/~
		// /images/2_38239823/test.png
		
		return "/images" + directoryName + file.getOriginalFilename();
		
	}
	
	// 파일 삭제 기능
	public static boolean removeFile(String filePath) {
		
		if(filePath == null) {
			logger.info("삭제 대상 파일 없음");
			return false;
		}
		
		// 실제 파일 저장 경로 찾기
		// /images 를 제거하고 나머지 부분을 FILE_UOLOAD_PATH에 이어 붙인다
		
		String fullFilePath = FILE_UPLOAD_PATH + filePath.replace("/images", "");
		Path path = Paths.get(fullFilePath);
		
		// 파일이 존재 하는지
		if(Files.exists(path)) {
			
			
			try {
				Files.delete(path);
			} catch (IOException e) {
				
				logger.error("removeFile : 파일 삭제 에러 " + fullFilePath);
				e.printStackTrace();
				return false;
			}
			
		}
		
		// 디렉토리 제거(심플할수록 좋음)
		Path dirPath = path.getParent();
		
		if(Files.exists(dirPath)) {
			try {
				Files.delete(dirPath);
			} catch (IOException e) {
				
				logger.error("removeFile : 디렉토리 삭제 에러 " + fullFilePath);
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
		
	}

}
