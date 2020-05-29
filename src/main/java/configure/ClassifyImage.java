package configure;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.visual_recognition.v3.model.ClassResult;
import com.ibm.watson.visual_recognition.v3.model.ClassifiedImage;
import com.ibm.watson.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.visual_recognition.v3.model.ClassifierResult;
import com.ibm.watson.visual_recognition.v3.model.ClassifyOptions;

public class ClassifyImage {
	private List<ClassResult> rest =  new ArrayList<>();
	public List<ClassResult> classfy(InputStream imageStream) throws FileNotFoundException {
		// TODO Auto-generated method stub

		IamAuthenticator authenticator = new IamAuthenticator("DMq67JSHpq1YbjR2tFljmtktrhSOXfusLF1y6hLMAt9z");
		VisualRecognition visualRecognition = new VisualRecognition("2018-03-19", authenticator);
		visualRecognition.setServiceUrl(
				"https://api.kr-seo.visual-recognition.watson.cloud.ibm.com/instances/27e64cbc-9a31-4b67-a036-b9888534d4f5");

		ClassifyOptions classifyOptions = new ClassifyOptions.Builder().imagesFile(imageStream)
				.imagesFilename("file.jpg").build();
		ClassifiedImages result = visualRecognition.classify(classifyOptions).execute().getResult();

		for (ClassifiedImage classifiedImage : result.getImages()) {
			for (ClassifierResult classifierResult : classifiedImage.getClassifiers()) {
				for (ClassResult classResult : classifierResult.getClasses()) {
					((List<ClassResult>) rest).add(classResult);
				}
			}
		}

		/*
		 * System.out.
		 * println("\n******** Classify with the General model ********\n" +
		 * result +
		 * "\n******** END Classify with the General model ********\n");
		 */
		return rest;

		/*
		 * text = result.toString(); return text;
		 */

	}
	public List<ClassResult> getRest() {
		return rest;
	}
	public void setRest(List<ClassResult> rest) {
		this.rest = rest;
	}
}
