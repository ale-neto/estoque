package configure;

import com.ibm.cloud.sdk.core.http.HttpConfigOptions;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.visual_recognition.v3.model.ClassifyOptions;

public class ClassifyImage {

	private String text;
	
	public String classfy (String imgURL) {
		// TODO Auto-generated method stub
		IamAuthenticator authenticator = new IamAuthenticator("DMq67JSHpq1YbjR2tFljmtktrhSOXfusLF1y6hLMAt9z");
		VisualRecognition service = new VisualRecognition("2020-02-14", authenticator);
		service.setServiceUrl("https://api.kr-seo.visual-recognition.watson.cloud.ibm.com/instances/27e64cbc-9a31-4b67-a036-b9888534d4f5");
		
		HttpConfigOptions configOptions = new HttpConfigOptions.Builder()
		  .disableSslVerification(true)
		  .build();
		service.configureClient(configOptions);
		ClassifyOptions options =  new ClassifyOptions.Builder().url(imgURL).build();
		ClassifiedImages result = service.classify(options).execute().getResult();
		
		text = result.toString();
		/*System.out.println("Classification Results:");
		System.out.println(text);*/
		return text;
		
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	

}
