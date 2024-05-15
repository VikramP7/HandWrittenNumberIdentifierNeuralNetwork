# Handwritten Number Identifier Neural Network
Started 2021 Updated 2024

An appication of my feed forward Neural Network Library, identifying hand written numbers using the MNIST dataset.

<p align="center">
<img src ="/img/three3-7.jpg" alt="Image of Handwritten number 3">
</p>

![Image of handwritten number 3][imgThree]

## Brief Description
This is a working example of an application of the fully connected feedforward neural network library I wrote in java.

The program creates a 4 layer neural network with 784 inputs and 10 outputs. Each input is a pixel value and each of the outputs is a a possible identification (0 through 9).

The network is then trained the gradient decent back propagation model on the MNIST dataset. With non-extensive (one pass over the data) training the network can reach accuracies greater than 80%.

![Image of Neural Network Testing Results][imgTestResult]

## Architecture

The network comprises of 784 inputs (one for each single channel pixel in the image), two hidden layer one of 32 nodes (after the input layer) and the other of 16 (before the output layer), and 10 outputs (one for each possible value 0 through 9).

This is a feed forward fully connected network, so each node in one layer is connected to each node on the next layer. 

## Training Data
The data used to train this neural network is from the [MNIST][MNISTlink] data set containing 70 000 images of handrwitten numbers with a resolution of 32x32 all labeled with what number they depict. 60 000 of the images are examples for training, and the remaining 10 000 are for testing.

## Training the Network
When training the network two main parameters contraolled how the neural network 'learns'. The network takes in a subset of the training data (*a batch*) and calculates tweaks that should be made to the weights and biases based on errors the network is making. 

The **Batch Size** is how large the subset of data is. When providing a larger batch sizes, each batch contains a more diverse range of inputs (images of different numbers). The network can average its changes accross these different number inputs to make changes that should act macroscopically better. This however has a draw back as tweaks are more general. 

The other parameter is the **Learning Rate**, it's scales the tweaks made to the weights and biases. Larger learning rates offten leader to faster training time however is less accurate as it causes over shoots in changes.

To learn more about how these parameters impact training take a look at the PDF report (MathReport), at the bottom of the document is some training testing.

## Project Story
This project was started in 2020, inspired by a [youtube video][linkPerceptron] I watched. After following along with the series creating my own versions in Java I realized that I understood the conseptual elements of the project but as a grade 10 at the time, I didn't have the background to fully understand the calculus.

The project reached a stand still as it was technically functioning but I didn't have the expertise to make improvement and keep the project going. Once I reached the end of grade 11 and had completed calc one. I had a much better understanding of the math involved and simultaneously had to complete a Math IB internal assessment (its a long math paper I had to write about any topic of my choice). It felt natural to use this opportunity to formally dive into the math behind neural networks.

The math report can found in this repo, it is written as a math report however it is a decent resource. If you interested in learning more about the project, or want to find some good resources to learn more (check its bibliography), or are interested in testing the proformance of this neural network, take a look at the report.

[imgThree]: /img/three3-7.jpg
[imgTestResult]: /img/test-results.png
[MNISTlink]: https://www.kaggle.com/datasets/hojjatk/mnist-dataset
[linkPerceptron]: https://www.youtube.com/watch?v=ntKn5TPHHAk