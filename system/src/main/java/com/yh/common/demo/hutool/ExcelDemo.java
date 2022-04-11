package com.yh.common.demo.hutool;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.yh.common.util.FileDownloadUtil;
import com.yh.system.domain.entity.sys.SysUser;
import org.apache.poi.ss.usermodel.DataFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class ExcelDemo {

	//excle数据读取
	public static void excelRead(MultipartFile file) {
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ExcelReader reader = ExcelUtil.getReader(inputStream);

		//maps则为以标题为key,每行数据为value,表示当前sheet下整个数据
		List<Map<String, Object>> maps = reader.readAll();
	}

	//数据导出
	public static void excelWrite(List<SysUser> data, HttpServletResponse response, String exportType) throws IOException {
		//
		ExcelWriter writer = ExcelUtil.getWriter();
		//标题设置
		writer.addHeaderAlias("name", "姓名");

		//只对已有别名数据进行导出
		writer.setOnlyAlias(true);
		//写入数据,强制写入标题
		writer.write(data, true);

		switch (exportType) {
			case "1":
				//将数据写入到流,并转为二进制数据(搭配spirngBoot中的 ResponseEntity<byte[]> 进行封装)
				byte[] bytes = FileDownloadUtil.handleExcelWriter(writer);
				break;
			case "2":
				//如果使用原生servlet,则直接获取response.getOutputStream()
				ServletOutputStream outputStream = response.getOutputStream();
				writer.flush(outputStream);
				break;
			case "3":
				//直接写入本地文件
				writer.flush(new File("D://test/test.xlsx"));
				break;
		}
	}

	//设置excel导出时的时间格式
	public static void setDateFormat(ExcelWriter excelWriter) {
		DataFormat dataFormat = excelWriter.getWorkbook().createDataFormat();
		excelWriter.getStyleSet().getCellStyleForDate().setDataFormat(dataFormat.getFormat("yyyy-MM-dd HH:mm:ss"));
	}

	//设置excel导出时,单元格自适应宽度
	public static void autoSizeColum(ExcelWriter excelWriter) {
		//需要保证数据已被写入
		excelWriter.autoSizeColumnAll();
	}
}
