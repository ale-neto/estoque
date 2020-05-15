package configure;

import java.io.FileNotFoundException;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.visual_recognition.v3.model.ClassifyOptions;



public class teste {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		IamAuthenticator authenticator = new IamAuthenticator("DMq67JSHpq1YbjR2tFljmtktrhSOXfusLF1y6hLMAt9z");
		VisualRecognition visualRecognition = new VisualRecognition("2018-03-19", authenticator);
		visualRecognition.setServiceUrl("https://api.kr-seo.visual-recognition.watson.cloud.ibm.com/instances/27e64cbc-9a31-4b67-a036-b9888534d4f5");

        ClassifyOptions classifyOptions = new ClassifyOptions.Builder().url("https://watson-developer-cloud.github.io/doc-tutorial-downloads/visual-recognition/640px-IBM_VGA_90X8941_on_PS55.jpg").build();
        ClassifiedImages result = visualRecognition.classify(classifyOptions).execute().getResult();
        System.out.println(
            "\n******** Classify with the General model ********\n" + result
                + "\n******** END Classify with the General model ********\n");
	}

}
