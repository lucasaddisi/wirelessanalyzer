package pipeAndFilter.impl;

import pipeAndFilter.Generator;
import pipeAndFilter.Pipe;

public abstract class GeneratorImpl<O> implements Generator<O>{

	protected Pipe<O> outputPipe;

	public GeneratorImpl(Pipe<O> outputPipe) {
		this.outputPipe = outputPipe;
	}

	
	@Override
	public void process() {
		if(this.canGenerate()){
	        
            O output = generate();
        
            this.outputPipe.accept(output);
        }
        
        else{
            this.outputPipe.closeForWritting();
        }
		
	}
}