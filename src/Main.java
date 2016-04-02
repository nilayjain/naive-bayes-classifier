public class Main {
	// The no. of workers in the training data
	public static int workers = 30;
	
	public static void main(String[] args){
		//Take an array of workers and assign them their work attributes.
		
		Worker[] arr = new Worker[workers];
		for(int i=0;i<workers;i++){
				arr[i] = new Worker();
		}
		
		// The Training Data Set:
		//the function populate, populates the training data set with workers attributes.
		populate(arr,0,"Service Technician", 6.00,180,12);
        populate(arr,1,"Service Technician", 5.92, 190, 11);
        populate(arr,2,"Service Technician", 5.58, 170, 12);
        populate(arr,3,"Service Technician", 5.92, 165, 10);
        populate(arr,4,"Service Technician", 5.82, 190, 10);
        populate(arr,6,"Service Technician", 5.70, 185, 9);
        populate(arr,7,"Service Technician", 5.80, 175, 10);
        populate(arr,8,"Service Technician", 5.79, 179, 13);
        populate(arr,9,"Service Technician", 5.90, 193, 11);
        
        populate(arr,10,"Auto Detailer", 5, 100, 6);
        populate(arr,11,"Auto Detailer", 5.5, 150, 8);
        populate(arr,12,"Auto Detailer", 5.42, 130, 7);
        populate(arr,13,"Auto Detailer", 5.75, 150, 9);
        populate(arr,14,"Auto Detailer", 5.3, 140, 7);
        populate(arr,15,"Auto Detailer", 5.20, 140, 6);
        populate(arr,16,"Auto Detailer", 5.30, 160, 7);
        populate(arr,17,"Auto Detailer", 5.40, 130, 8);
        populate(arr,18,"Auto Detailer", 5.10, 120, 9);
        populate(arr,19,"Auto Detailer", 5.25, 118, 8);
        
        populate(arr,20,"General Laborer", 4, 200, 5); 
        populate(arr,21,"General Laborer", 4.10, 150, 8); 
        populate(arr,22,"General Laborer", 5.42, 190, 7); 
        populate(arr,23,"General Laborer", 5.50, 150, 5);
        populate(arr,24,"General Laborer", 4.50, 150, 4);
        populate(arr,25,"General Laborer", 5.50, 180, 6);
        populate(arr,26,"General Laborer", 5.00, 160, 5);
        populate(arr,27,"General Laborer", 5.15, 170, 7);
        populate(arr,28,"General Laborer", 5.20, 180, 5);
        populate(arr,29,"General Laborer", 4.80, 190, 8);
        
        MathFunc mf = new MathFunc();
        /*The function posterior trains from the above data and assigns each new worker a job.
        To test the code and see what job is assigned to any new worker can be added in the following format:
        	mf.posterior(arr,height,weight,intelligence)
        	height in feet.
        	weight in pounds(lbs).
        	intelligence in units with scale(4-12).
        */
        mf.posterior(arr,5,150,16);
        mf.posterior(arr,4,110,5);
        mf.posterior(arr,4.3,130,5);
        mf.posterior(arr,5.1,130,6);
        mf.posterior(arr,6,190,6);
        mf.posterior(arr,4.5,150,4);
	}
	//This function populates the training data set with workers attributes.
	public static void populate(Worker arr[],int i,String s,double d1,double d2,double d3){
			arr[i].job = s;
			arr[i].height = d1;
			arr[i].weight = d2;
			arr[i].intelligence = d3;
			return;
	}
}
