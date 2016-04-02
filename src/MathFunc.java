public class MathFunc {
	
	/*Gives average height(col==1) or weight(col==2) or intelligence(col==3) of all the 3 category of workers 
	 *in the factory. 
	 */
	public Tuple average(Worker arr[],int col){
		Tuple av = new Tuple();
		av.d1 = 0.0;
		av.d2 = 0.0;
		av.d3 = 0.0;
		
		for(int i=0;i<Main.workers;i++) {
			if(i<Main.workers/3){
				if(col==1) av.d1+=arr[i].height;
				if(col==2) av.d1+=arr[i].weight;
				if(col==3) av.d1+=arr[i].intelligence;
			}
			else if(i<2*Main.workers/3){
				if(col==1) av.d2+=arr[i].height;
				if(col==2) av.d2+=arr[i].weight;
				if(col==3) av.d2+=arr[i].intelligence;
			}
			else{
				if(col==1) av.d3+=arr[i].height;
				if(col==2) av.d3+=arr[i].weight;
				if(col==3) av.d3+=arr[i].intelligence;
			}
		}
		// The average of the 3 worker attributes height,weight and intelligence is calculated.
		av.d1 = av.d1/(Main.workers/3);
		av.d2 = av.d2/(Main.workers/3);
		av.d3 = av.d3/(Main.workers/3);
		return av;
	}
	
	public Tuple variance(Worker arr[],int col){
		Tuple var= new Tuple();
		Tuple av= new Tuple();
		var.d1 = 0.0;
		var.d2 = 0.0;
		var.d3 = 0.0;
		av = average(arr,col);
		for(int i=0;i<Main.workers;i++){
			if(i<Main.workers/3){
				//System.out.println("loop");
				if(col==1) var.d1 = var.d1 +(arr[i].height-av.d1)*(arr[i].height-av.d1);
				if(col==2) var.d1 = var.d1 +(arr[i].weight-av.d1)*(arr[i].weight-av.d1);
				if(col==3) var.d1 = var.d1 +(arr[i].intelligence-av.d1)*(arr[i].intelligence-av.d1);
			}
			else if(i<2*Main.workers/3){
				if(col==1) var.d2 = var.d2 +(arr[i].height-av.d2)*(arr[i].height-av.d2);
				if(col==2) var.d2 = var.d2 +(arr[i].weight-av.d2)*(arr[i].weight-av.d2);
				if(col==3) var.d2 = var.d2 +(arr[i].intelligence-av.d2)*(arr[i].intelligence-av.d2);
			}
			else{
				if(col==1) var.d3 = var.d3 +(arr[i].height-av.d3)*(arr[i].height-av.d3);
				if(col==2) var.d3 = var.d3 +(arr[i].weight-av.d3)*(arr[i].weight-av.d3);
				if(col==3) var.d3 = var.d3 +(arr[i].intelligence-av.d3)*(arr[i].intelligence-av.d3);
			}
		}
		// The variance of the 3 worker attributes height, weight and intelligence is calculated.
		var.d1/=(Main.workers/3);
		var.d2/=(Main.workers/3);
		var.d3/=(Main.workers/3);
		return var;
	}
	
	/*This function calculates the posterior probabilities of workers being assigned different jobs
	 * namely : technician, detailer, and laborer.
	 * The function then selects the job with highest posterior probability and assigns the worker
	 * that job and prints it to console. 
	 */
	public void posterior(Worker arr[],double height,double weight,double intelligence){
		/* An array of tuples to calculate the posterior probability of each worker attribute independently
		 * for each job. Initialize to zero.
		 */
		Tuple[] t = new Tuple[3];
		for(int i=0;i<3;i++){
				t[i] = new Tuple();
				t[i].d1 = 0.0;
				t[i].d2 = 0.0;
				t[i].d3 = 0.0;
		}
		
		/* to calculate average and variance for each of worker attributes(height,weight,intelligence)
		 * for each job (technician,detailer,laborer).
		 */
		Tuple av = new Tuple();
		Tuple var = new Tuple();
		
		/* Calculate posterior probability assuming worker is a technician*/
		var = variance(arr,1);
		av = average(arr,1);
		t[0].d1 = (1/Math.sqrt(2 * Math.PI * var.d1)) * Math.pow(Math.E,-((height-av.d1)*(height-av.d1))/(2*var.d1));
		t[0].d2 = (1/Math.sqrt(2 * Math.PI * var.d2)) * Math.pow(Math.E,-((height-av.d2)*(height-av.d2))/(2*var.d2));
		t[0].d3 = (1/Math.sqrt(2 * Math.PI * var.d3)) * Math.pow(Math.E,-((height-av.d3)*(height-av.d3))/(2*var.d3));

		/* Calculate posterior probability assuming worker is a detailer*/
		var = variance(arr,2);
		av = average(arr,2);
		t[1].d1 = (1/Math.sqrt(2 * Math.PI * var.d1)) * Math.pow(Math.E,-((weight-av.d1)*(weight-av.d1))/(2*var.d1));
		t[1].d2 = (1/Math.sqrt(2 * Math.PI * var.d2)) * Math.pow(Math.E,-((weight-av.d2)*(weight-av.d2))/(2*var.d2));
		t[1].d3 = (1/Math.sqrt(2 * Math.PI * var.d3)) * Math.pow(Math.E,-((weight-av.d3)*(weight-av.d3))/(2*var.d3));
		
		/* Calculate posterior probability assuming worker is a laborer*/
		var = variance(arr,3);
		av = average(arr,3);
		t[2].d1 = (1/Math.sqrt(2 * Math.PI * var.d1)) * Math.pow(Math.E,-((intelligence-av.d1)*(intelligence-av.d1))/(2*var.d1));
		t[2].d2 = (1/Math.sqrt(2 * Math.PI * var.d2)) * Math.pow(Math.E,-((intelligence-av.d2)*(intelligence-av.d2))/(2*var.d2));
		t[2].d3 = (1/Math.sqrt(2 * Math.PI * var.d3)) * Math.pow(Math.E,-((intelligence-av.d3)*(intelligence-av.d3))/(2*var.d3));
		
		/* Multiply the posterior probabilities of different worker attributes for the worker being a technician 
		 * as they are independent of each other according to the naive bayes assumption. 
		 * Weight(no. of samples) of each job in the training data is same so no need to multiply by weight.
		 * */
		double technician = t[0].d1 * t[1].d1 * t[2].d1;
		
		/* Multiply the posterior probabilities of different worker attributes for the worker being a detailer */
		double detailer = t[0].d2 * t[1].d2 * t[2].d2;
		
		/* Multiply the posterior probabilities of different worker attributes for the worker being a laborer */
		double laborer = t[0].d3 * t[1].d3 * t[2].d3;
		
		/* Select the job having highest posterior probability and print it.*/
		if(technician > detailer && technician > laborer) {
			System.out.println("The worker with work attributes:");
			System.out.printf("height = %.2f, weight = %.2f, intelligence = %.2f should work as Service Technician",height,weight,intelligence);
			System.out.println("");
			System.out.println("");
		}else if(detailer > technician  && detailer > laborer){
			System.out.println("The worker with work attributes:");
			System.out.printf("height = %.2f, weight = %.2f, intelligence = %.2f should work as Auto Detailer",height,weight,intelligence);
			System.out.println("");
			System.out.println("");
		}else{
			System.out.println("The worker with work attributes:");
			System.out.printf("height = %.2f, weight = %.2f, intelligence = %.2f should work as General Laborer",height,weight,intelligence);
			System.out.println("");
			System.out.println("");
		}
			
	}
}
