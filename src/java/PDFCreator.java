package java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


public class PDFCreator 
{
	private UUID docID;
	private String workDir;
	private String outputDir;
	private String consoleOutput;
	
	public PDFCreator() throws IOException
	{
		 this.docID = UUID.randomUUID();
		 this.workDir = Files.createTempDirectory("PDF_").toString() + "/" + this.docID.toString() + "/";
		 
		 File workDirFile = new File(this.workDir);		 
		 workDirFile.mkdirs();
		 
		 
	}
	
	public PDFCreator(String outputPath) throws IOException
	{
		this.docID = UUID.randomUUID();
		 this.workDir = Files.createTempDirectory("PDF_").toString() + "/" + this.docID.toString() + "/";
		 
		 File workDirFile = new File(this.workDir);
		 File outputDirFile = new File(this.outputDir);
		 
		 workDirFile.mkdirs();
		 outputDirFile.mkdirs();
	}
	
	public String compileString(String texStr)
	{
		try 
		{
			File outFile = new File(this.workDir + "work.tex");
			//System.out.println(outFile.getAbsolutePath());
			PrintWriter out = new PrintWriter(outFile, "UTF-8");
			out.write(texStr);
			out.close();
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) 
		{
			e1.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		String outPath = "";
		try 
		{
			outPath = this.compileFile(this.workDir + "work.tex");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		String targetPath;
		try 
		{
			targetPath = this.outputDir + "/" + this.docID.toString() + ".pdf";

			Files.move(Paths.get(outPath), Paths.get(targetPath));
			
			this.recursiveDelete(new File(this.workDir));
			
			return targetPath;
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return "";

		
	}
	
	public String compileFile(String texPath) throws IOException
	{
		Runtime r = Runtime.getRuntime();
		String output = "";
		Process p = r.exec("pdflatex -interaction nonstopmode -halt-on-error -file-line-error -output-directory="+ this.workDir +" -aux-directory="+ this.workDir +"/aux "+ texPath);
		try 
		{
			p.waitFor();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = "";

		while ((line = b.readLine()) != null) {
			output += line + "\n";
		}

		b.close();
		
		this.consoleOutput = output;
		
		return this.workDir + texPath.substring(texPath.lastIndexOf("/") + 1).replace(".tex", ".pdf");
	}
	
	
	public String getWorkDir() {
		return workDir;
	}
	
	public String getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
		
		File outputDirFile = new File(this.outputDir);
		outputDirFile.mkdirs();
	}
	
	public UUID getUUID() {
		return this.docID;
	}
	
	public String getConsoleOutput() {
		return this.consoleOutput;
	}

	
	private static void recursiveDelete(File file) {
        //to end the recursive loop
        if (!file.exists())
            return;
         
        //if directory, go inside and call recursively
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                //call recursively
                recursiveDelete(f);
            }
        }
        //call delete to delete files and empty directory
        file.delete();
        System.out.println("Deleted file/folder: "+file.getAbsolutePath());
    }

}