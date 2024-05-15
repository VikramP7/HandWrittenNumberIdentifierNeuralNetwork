import java.util.Scanner;

public class HandWrittenNumberIdentifierNeuralNetwork {

	public static void main(String[] args) {
		// import training and testing data from training data folder
		AIImageList trainingAIImages = new AIImageList(
				"./TrainingData/train-images.idx3-ubyte",
				"./TrainingData/train-labels.idx1-ubyte");
		AIImageList testAIImages = new AIImageList(
				"./TrainingData/t10k-images.idx3-ubyte",
				"./TrainingData/t10k-labels.idx1-ubyte");

		// create neural network
		NeuralNetwork neuralNetwork = new NeuralNetwork(784, new int[] { 32, 16 }, 10);

		// Create Training Resources
		float[][] trainingInputs = new float[trainingAIImages.numberOfImages][trainingAIImages.imageList[0].imageArr.length];
		float[][] trainingTargets = new float[trainingAIImages.numberOfImages][10];

		// loop through ever image in the training data
		for (int i = 0; i < trainingAIImages.numberOfImages; i++) {
			// loop through ever pixel in the image
			for (int f = 0; f < trainingAIImages.imageList[i].imageArr.length; f++) {
				// normalize the black and white [0,255] scale to [0,1]
				trainingInputs[i][f] = (trainingAIImages.imageList[i].imageArr[f]) / 255.0f;
			}

			// create target array using labels
			for (int f = 0; f < trainingTargets[i].length; f++) {
				trainingTargets[i][f] = 0.0f;
			}
			trainingTargets[i][trainingAIImages.imageList[i].label] = 1.0f;
		}

		// Create Testing Resources
		float[][] testingInputs = new float[testAIImages.numberOfImages][testAIImages.imageList[0].imageArr.length];
		float[][] testingTargets = new float[testAIImages.numberOfImages][10];

		// loop through ever image in the training data
		for (int n = 0; n < testAIImages.numberOfImages; n++) {
			// loop through ever pixel in the image
			for (int f = 0; f < testAIImages.imageList[n].imageArr.length; f++) {
				// normalize the black and white [0,255] scale to [0,1]
				testingInputs[n][f] = (testAIImages.imageList[n].imageArr[f]) / 255.0f;
			}

			// create target array using labels
			for (int f = 0; f < testingTargets[n].length; f++) {
				testingTargets[n][f] = 0.0f;
			}
			testingTargets[n][testAIImages.imageList[n].label] = 1.0f;
		}

		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Hand Written Number Identifier Neural Network!");
		System.out.println("To begiun training enter the training parameters:");
		System.out.println("Enter the Learning rate [float] (default is 3.0): ");
		String learningRateStr = scanner.nextLine();
		System.out.println("Enter the Batch Size [int] (default is 25): ");
		String batchSizeStr = scanner.nextLine();
		System.out.println("Enter the number of itterations [int] (default is 1): ");
		String itterationsStr = scanner.nextLine();

		int batchSize = 25;
		float learningRate = 3.0f;
		int itterations = 1;

		try {
			batchSize = Integer.parseInt(batchSizeStr);
			learningRate = Float.parseFloat(learningRateStr);
			itterations = Integer.parseInt(itterationsStr);
		} catch (NumberFormatException e) {
			System.out.println("Error parsing inputs using default parameters!");
			batchSize = 25;
			learningRate = 3.0f;
			itterations = 1;
		}

		// set learning rate
		neuralNetwork.SetLearningRate(learningRate);

		// Training
		neuralNetwork.Train(trainingInputs, trainingTargets, batchSize, itterations,
				NeuralNetwork.PrintProgress.PRINTPROGRESS);

		// neuralNetwork.PrintNetwork();

		// Testing
		// neuralNetwork.Test(testingInputs, testingTargets,
		// PrintProgress.DONTPRINTPROGRESS, ResultType.CHOICE, PrintResult.UGLYPRINT);
		neuralNetwork.Test(testingInputs, testingTargets,
				NeuralNetwork.PrintProgress.DONTPRINTPROGRESS,
				NeuralNetwork.ResultType.CHOICE,
				NeuralNetwork.PrintResult.PRETTYPRINT);

		boolean lop = true;
		while (lop) {
			int userinput = 0;
			boolean pooUserInput = true;
			while (pooUserInput) {
				pooUserInput = false;
				System.out.println("Image Number from 0-9999 (Type \"quit\" to quit): ");
				String userInStr = scanner.nextLine();
				if (userInStr.toLowerCase().equals("quit")) {
					lop = false;
					pooUserInput = false;
				} else {
					try {
						userinput = Integer.parseInt(userInStr);
					} catch (NumberFormatException e) {
						System.out.println("That is not a integer or float value, please provide a number.");
						pooUserInput = true;
					}
					if (userinput > 9999 | userinput < 0) {
						System.out.println("Image index out of range! Choose a number between 0 and 9999");
						pooUserInput = true;
					}
				}
			}

			float[] inputs = new float[testAIImages.imageList[userinput].imageArr.length];
			for (int f = 0; f < testAIImages.imageList[userinput].imageArr.length; f++) {
				inputs[f] = (testAIImages.imageList[userinput].imageArr[f]) / 255.0f;
			}

			testAIImages.imageList[userinput].DrawASCIIImage();
			neuralNetwork.FeedForward(inputs, NeuralNetwork.PrintResult.PRETTYPRINT);
		}
		scanner.close();
	}
}
