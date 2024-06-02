package com.cuahangbansach.cuahangbansach_java.Service;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class WordConvert {
//    private String getDocxHtmlText(byte[] contents) throws IOException, FileNotFoundException {
//        File file = new java.io.File("reportTemplate.docx");
//        FileUtils.writeByteArrayToFile(file, contents);
//        InputStream in = new FileInputStream(file);
//        XWPFDocument document = new XWPFDocument(in);
//        XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(new File("word/media")));
//        OutputStream out = new ByteArrayOutputStream();
//        XHTMLConverter.getInstance().convert(document, out, options);
//        in.close();
//        out.close();
//        file.delete();
//        return out.toString();
//    }
}
