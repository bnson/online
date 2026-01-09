class AI {

    constructor(name, synth) {
		this.name = name;
		this.synth = synth;
		
		//--
		this.voices = synth.getVoices();
		this.voices.forEach((voice, index) => {
			console.log(voice.name + ' - [' + voice.lang + ']');
		});
		
		this.voiceName = this.voices[0].name;
		this.voiceIndex = 0;
		console.log(`Current voice: ${this.voiceName}, index: ${this.voiceIndex}`);
		
        this.volume = 1;
        this.pitch = 1;
        this.rate = 1;		
		
		//--
		this.isStopped = true;
    }
	
    setVoice(voiceName) {
        if (voiceName) {
			this.voiceName = voiceName;
            this.voiceIndex = this.voices.findIndex(x => x.name === voiceName);
			//console.log(`Set current voice: ${this.voiceName}, index: ${this.voiceIndex}`);
        }
    }
	
	speak(text) {
		this.utter = new SpeechSynthesisUtterance();
		this.utter.voice = this.voices[this.voiceIndex];
		this.utter.volume = this.volume;
		this.utter.pitch = this.pitch;
		this.utter.rate = this.rate;
		this.utter.text = text;
		
		this.synth.speak(this.utter);
		
		this.utter.onstart = () => {
			this.isStopped = false;
		};
		
		this.utter.onend = () => {
			this.isStopped = true;
		};
		
	}

}
