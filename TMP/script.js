const TITLES = "Từ Vựng Tiếng Anh Ngành Công Nghệ (Phần 1)↨Technology Vocabulary (Part 1)".split("↨");
const VOCABULARIES = "printer↔/ˈprɪntər/↔máy in↨scanner↔/ˈskænər/↔máy quét↨copier↔/ˈkɒpiər/ (US /ˈkɑːpiər/)↔máy photocopy".split("↨");
//const VOCABULARIES = "printer↔/ˈprɪntər/↔máy in↨scanner↔/ˈskænər/↔máy quét↨copier↔/ˈkɒpiər/ (US /ˈkɑːpiər/)↔máy photocopy↨fax machine↔/ˈfæks məˌʃiːn/↔máy fax↨shredder↔/ˈʃrɛdər/↔máy hủy tài liệu↨laminator↔/ˈlæməˌneɪtər/↔máy ép plastic↨paper cutter↔/ˈpeɪpər ˈkʌtər/↔máy cắt giấy↨projector↔/prəˈdʒektər/↔máy chiếu↨projector screen↔/prəˈdʒektər skriːn/↔màn chiếu↨whiteboard↔/ˈwaɪtbɔːrd/↔bảng trắng↨conference phone↔/ˈkɒnfərəns foʊn/↔điện thoại hội nghị↨conference speakerphone↔/ˈkɒnfərəns ˈspiːkərfoʊn/↔loa thoại hội nghị↨microphone↔/ˈmaɪkrəˌfoʊn/↔micrô↨headphones↔/ˈhɛdfoʊnz/↔tai nghe chụp tai↨noise-cancelling headphones↔/ˌnɔɪz ˈkænsəlɪŋ ˈhɛdfoʊnz/↔tai nghe chống ồn↨earphones↔/ˈɪrfoʊnz/↔tai nghe nhét tai (có dây)↨earbuds↔/ˈɪrbʌdz/↔tai nghe nhét tai (không dây)↨speaker↔/ˈspiːkər/↔loa↨bluetooth speaker↔/ˈbluːtuːθ ˈspiːkər/↔loa Bluetooth↨soundbar↔/ˈsaʊndbɑːr/↔loa dạng thanh↨amplifier↔/ˈæmplɪˌfaɪər/↔bộ khuếch đại âm thanh↨audio mixer↔/ˈɔːdioʊ ˈmɪksər/↔bộ trộn âm thanh".split("↨");

const FADE_IN = 300;
const FADE_OUT = 300;
const TEMPLATE_DIV_VOCABULARY  = "<div class=\"div-vocabulary bg-white\">$1</div>";
const TEMPLATE_PATH_IMAGE  = "$1-001.png";

const SYNTH = window.speechSynthesis;

const VOICES = [
	"Microsoft An - Vietnamese (Vietnam)",
	"Microsoft David - English (United States)",
	"Microsoft Mark - English (United States)",
	"Microsoft Zira - English (United States)"
]; 

let aiSliver;
let voiceIndex = 0;

let repeatMax = 4;
let repeatIndex = 1;

let titleIndex = 0;
let vocabularyIndex = 0;


//=================================================================================
function loadTitle() {
	titleEn = "<span class=\"title-video\">" + TITLES[1] + "</span>";
	titleVn = "<span class=\"title-video\">" + "Từ Vựng Tiếng Anh Ngành<br>Công Nghệ (Phần 1)" + "</span>";
	
	$("#span-english").html(titleEn);
	$("#span-vietnamese").html(titleVn);	
}

function loadVocabularyList() {
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
		if (titleIndex <= 1) {
			aiSliver.setVoice(VOICES[titleIndex]);
			aiSliver.speak(TITLES[titleIndex]);
			
			titleIndex++;
			
			aiSliver.utter.onend = () => {
				read();
			};
		} else {
			readVocabulary();
		}
	}, 1000);
}

function readVocabulary() {
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
			
			let pathImage = TEMPLATE_PATH_IMAGE.replace("$1", vocabularyEn);
			$("#img-display").attr("src", pathImage);
			$("#img-display").fadeIn(FADE_IN);
			
			//--
			aiSliver.setVoice(VOICES[voiceIndex]);

			if (voiceIndex == 0) {
				aiSliver.speak(vocabularyVn);
			} else {
				aiSliver.speak(vocabularyEn);
			}
			
			if (voiceIndex < 3) {
				voiceIndex++;
			} else {
				voiceIndex = 0;
			}			
			
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
					$("#img-display").fadeOut(FADE_OUT);		
				}			
				//--
				read();	
			};			
		} else {
			console.log("================================");
			$("#span-english").html("<span style=\"font-size: 120px;\">END");
			$("#span-english").fadeIn(FADE_IN);
		}
	}, 1000);
	
}

//=================================================================================
//== PAGE IS READY ==
$(function() {
	loadAi();
	loadTitle();
	loadVocabularyList();
	
    $("body").on("keydown", function (e) {
        console.log(e.which);
        if (e.which == 113) {
            read();
        }
    });	

});

//== PAGE BEFORE UNLOAD ==
$(window).on('beforeunload', function () {
    ai.getSynth().cancel();
    ai = null;
});
