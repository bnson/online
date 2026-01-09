const VOCABULARIES = "printer↔/ˈprɪntər/↔máy in↨scanner↔/ˈskænər/↔máy quét↨copier↔/ˈkɒpiər/ (US /ˈkɑːpiər/)↔máy photocopy".split("↨");

const FADE_IN = 300;
const FADE_OUT = 300;
const TEMPLATE_DIV_VOCABULARY  = "<div class=\"div-vocabulary bg-white\">$1</div>"

const SYNTH = window.speechSynthesis;

const VOICES = [
	"Microsoft David - English (United States)",
	"Microsoft Mark - English (United States)",
	"Microsoft Zira - English (United States)",
	"Microsoft An - Vietnamese (Vietnam)"
]; 

let aiSliver;
let voiceIndex = 0;

let repeatMax = 3;
let repeatIndex = 1;

let vocabularyIndex = 0;

//=================================================================================
function loadDivVocabularyList() {

	VOCABULARIES.forEach(function (item, index) {
		item = TEMPLATE_DIV_VOCABULARY.replace("$1", item.split("↔")[0]);
		$("#div-vocabulary-list").append(item);
	});
	
}

function trimSpace(str) {
    return str.trim().replace(/\s+/g, ' ');
}


//=================================================================================
function loadAi() {
    aiSliver = new AI('Sliver', SYNTH);
	aiSliver.setVoice('Microsoft Zira - English (United States)');
}

function read() {
	setTimeout(function () {
		if (vocabularyIndex <= (VOCABULARIES.length - 1)) {
			let vocabulary = VOCABULARIES[vocabularyIndex].split("↔");
			let vocabularyEn = vocabulary[0];
			let vocabularyIpa = vocabulary[1];
			let vocabularyVn = vocabulary[2];
			console.log(`Read: ${vocabularyEn}`);
			
			//--
			$("#span-english").html(vocabularyEn);
			$("#span-english").fadeIn(FADE_IN);
			$("#span-vietnamese").html(vocabularyVn);
			$("#span-vietnamese").fadeIn(FADE_IN);	
			$("#span-ipa").html(vocabularyIpa);
			$("#span-ipa").fadeIn(FADE_IN);					
			
			//--
			aiSliver.setVoice(VOICES[voiceIndex]);
			if (voiceIndex < 2) {
				voiceIndex++;		
			} else {
				voiceIndex = 0;
			}
			
			//--
			aiSliver.speak(vocabularyEn);
			aiSliver.utter.onend = () => {
				//--
				if (repeatIndex < repeatMax) {
					repeatIndex++;
				} else {
					repeatIndex = 1;
					vocabularyIndex++; 
					//--
					$("#span-english").fadeOut(FADE_OUT);
					$("#span-vietnamese").fadeOut(FADE_OUT);	
					$("#span-ipa").fadeOut(FADE_OUT);							
				}			
				//--
				read();	
			};			
		} else {
			
		}
	}, 1000);
	
}

//=================================================================================
//== PAGE IS READY ==
$(function() {
	loadAi();
	loadDivVocabularyList();
	read();
});

//== PAGE BEFORE UNLOAD ==
$(window).on('beforeunload', function () {
    ai.getSynth().cancel();
    ai = null;
});
