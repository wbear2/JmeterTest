package cn.sjtu.edu.se.dclab.stresstest;

import java.util.List;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class UploadDataTest extends AbstractJavaSamplerClient{
	
	private String urlStr = null;
	private SampleResult results;

	@Override
	public SampleResult runTest(JavaSamplerContext jsc) {
		
		//System.out.println("runTest...");
		
		results.sampleStart();
		
		try{
			//1 means one record,100 means at most 100 types.
			KpdFactory kf = new KpdFactory(1,100);
			List<Kpd> kpds = kf.getList();
			
			UploadData ud = new UploadData(urlStr);
			ud.doPut(kpds);
		}catch(Throwable e){
			results.setSuccessful(true);
		}finally{
			results.sampleEnd();
		}
		
		return results;
	}
	
	public Arguments getDefaultParameters(){
		Arguments params = new Arguments();
		params.addArgument("urlStr", "127.0.0.1");
		return params;
	}
	
	public void setupTest(JavaSamplerContext jsc){
		
		//System.out.println("setupTest...");
		
		results = new SampleResult();
		urlStr = jsc.getParameter("urlStr");
		if(urlStr != null)
			results.setSamplerData(urlStr);
	}
	
	public void teardownTest(JavaSamplerContext jsc){
		
	}
	
	public static void main(String[] args){
		Arguments a = new Arguments();
		a.addArgument("urlStr", "http://103.6.221.228:8888/");
		JavaSamplerContext jsc = new JavaSamplerContext(a);
		
		UploadDataTest udt = new UploadDataTest();
		udt.setupTest(jsc);
		udt.runTest(jsc);
		udt.teardownTest(jsc);
	}

}
