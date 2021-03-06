package com.dazzilove.dd.testdb;

import com.dazzilove.dd.domain.Word;
import com.dazzilove.dd.domain.WordUnit;
import com.dazzilove.dd.service.WordUnitExamService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class TempDbInsertTest {

	@Autowired
	WordUnitExamService wordTestService;

	@Test
	@Ignore
	public void tempWordTestDbInsert() throws JSONException {
		String tempDb = "";

		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		jsonObject = new JSONObject();jsonObject.put("key", "a");jsonObject.put("value", "하나의");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "able");jsonObject.put("value", "~할 수 있는");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "after");jsonObject.put("value", "뒤에, 다음에");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "an");jsonObject.put("value", "하나의");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "and");jsonObject.put("value", "그리고");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "animal");jsonObject.put("value", "동물");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "ant");jsonObject.put("value", "개미");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "apple");jsonObject.put("value", "사과");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "at");jsonObject.put("value", "~때에(시간), ~에서(장소)");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "baby");jsonObject.put("value", "아기");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "bag");jsonObject.put("value", "가방");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "be");jsonObject.put("value", "~이다, ~이 되다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "bear");jsonObject.put("value", "곰, (아이를)낳다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "big");jsonObject.put("value", "큰");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "body");jsonObject.put("value", "몸");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "book");jsonObject.put("value", "책");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "boot");jsonObject.put("value", "장화");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "by");jsonObject.put("value", "~옆에");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "bye");jsonObject.put("value", "안녕, 잘가");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "can");jsonObject.put("value", "깡통, ~을 할 수 있다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "cap");jsonObject.put("value", "모자");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "cat");jsonObject.put("value", "고양이");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "chair");jsonObject.put("value", "의자");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "chicken");jsonObject.put("value", "닭");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "child");jsonObject.put("value", "어린이");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "close");jsonObject.put("value", "닫다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "cold");jsonObject.put("value", "차가운");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "come");jsonObject.put("value", "오다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "cow");jsonObject.put("value", "소");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "cut");jsonObject.put("value", "베다, 자르다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "dad");jsonObject.put("value", "아빠");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "dance");jsonObject.put("value", "춤추다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "desk");jsonObject.put("value", "책상");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "do");jsonObject.put("value", "하다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "dog");jsonObject.put("value", "개");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "dolphin");jsonObject.put("value", "돌고래");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "door");jsonObject.put("value", "문");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "down");jsonObject.put("value", "아래로(에)");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "draw");jsonObject.put("value", "그리다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "duck");jsonObject.put("value", "오리");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "easy");jsonObject.put("value", "쉬운");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "eye");jsonObject.put("value", "눈");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "face");jsonObject.put("value", "얼굴");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "finger");jsonObject.put("value", "손가락");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "fish");jsonObject.put("value", "물고기");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "fly");jsonObject.put("value", "파리, 날다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "foot");jsonObject.put("value", "발");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "for");jsonObject.put("value", "~을 위하여");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "glad");jsonObject.put("value", "기쁜");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "go");jsonObject.put("value", "가다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "great");jsonObject.put("value", "훌륭한");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "hand");jsonObject.put("value", "손");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "happy");jsonObject.put("value", "행복한");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "hat");jsonObject.put("value", "모자");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "have");jsonObject.put("value", "가지고 있다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "hello");jsonObject.put("value", "안녕");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "help");jsonObject.put("value", "돕다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "here");jsonObject.put("value", "여기에");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "hi");jsonObject.put("value", "안녕");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "horse");jsonObject.put("value", "말");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "how");jsonObject.put("value", "어떻게, 얼마나");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "hungry");jsonObject.put("value", "배고픈");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "I");jsonObject.put("value", "나");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "it");jsonObject.put("value", "그것");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "jam");jsonObject.put("value", "잼");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "jump");jsonObject.put("value", "뛰다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "knee");jsonObject.put("value", "무릎");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "leg");jsonObject.put("value", "다리");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "let");jsonObject.put("value", "~하게 하다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "like");jsonObject.put("value", "좋아하다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "lion");jsonObject.put("value", "사자");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "look");jsonObject.put("value", "보다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "lunch");jsonObject.put("value", "점심식사");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "make");jsonObject.put("value", "만들다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "many");jsonObject.put("value", "(수가) 많은");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "milk");jsonObject.put("value", "우유");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "mom");jsonObject.put("value", "엄마");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "monkey");jsonObject.put("value", "원숭이");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "mouth");jsonObject.put("value", "입");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "name");jsonObject.put("value", "이름");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "neck");jsonObject.put("value", "목");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "no(nope)");jsonObject.put("value", "아니오, 하나도 없는");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "nose");jsonObject.put("value", "코");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "not");jsonObject.put("value", "~않다, ~가 아니다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "number");jsonObject.put("value", "숫자");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "oh");jsonObject.put("value", "오, 아");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "OK");jsonObject.put("value", "좋아");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "on");jsonObject.put("value", "~위에");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "open");jsonObject.put("value", "열다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "out");jsonObject.put("value", "밖으로");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "outside");jsonObject.put("value", "~의 밖에");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "page");jsonObject.put("value", "쪽");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "pants");jsonObject.put("value", "바지");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "pear");jsonObject.put("value", "(먹는) 배");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "pencil");jsonObject.put("value", "연필");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "pig");jsonObject.put("value", "돼지");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "please");jsonObject.put("value", "부디, 제발");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "pocket");jsonObject.put("value", "주머니");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "point");jsonObject.put("value", "가리키다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "put");jsonObject.put("value", "두다, 놓다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "rain");jsonObject.put("value", "비, 비가 오다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "run");jsonObject.put("value", "달리다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "seat");jsonObject.put("value", "좌석");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "ship");jsonObject.put("value", "배");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "shoe");jsonObject.put("value", "신발, 구두");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "shoulder");jsonObject.put("value", "어깨");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "sit");jsonObject.put("value", "앉다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "skirt");jsonObject.put("value", "치마");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "small");jsonObject.put("value", "작은, 적은");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "snow");jsonObject.put("value", "눈, 눈이 내리다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "so");jsonObject.put("value", "아주, 매우");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "sorry");jsonObject.put("value", "미안한");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "stand");jsonObject.put("value", "서다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "star");jsonObject.put("value", "별");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "story");jsonObject.put("value", "이야기");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "student");jsonObject.put("value", "학생");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "sun");jsonObject.put("value", "태양");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "sure");jsonObject.put("value", "틀림없는");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "swim");jsonObject.put("value", "수영하다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "thank");jsonObject.put("value", "감사하다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "that");jsonObject.put("value", "저, 저것");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "the");jsonObject.put("value", "그, 저");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "this");jsonObject.put("value", "이, 이것");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "time");jsonObject.put("value", "시간");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "too");jsonObject.put("value", "또한, 너무");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "tooth");jsonObject.put("value", "이, 치아");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "tree");jsonObject.put("value", "나무");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "up");jsonObject.put("value", "위로");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "wait");jsonObject.put("value", "기다리다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "wash");jsonObject.put("value", "씻다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "welcome");jsonObject.put("value", "환영하다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "what");jsonObject.put("value", "무엇");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "window");jsonObject.put("value", "창문");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "wow");jsonObject.put("value", "와");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "yes");jsonObject.put("value", "예, 네");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "you");jsonObject.put("value", "너");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "afternoon");jsonObject.put("value", "오후");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "air");jsonObject.put("value", "공기");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "all");jsonObject.put("value", "모든것(의)");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "a.m.(A.M.)");jsonObject.put("value", "오전");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "answer");jsonObject.put("value", "대답, 대답하다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "bad");jsonObject.put("value", "나쁜");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "balloon");jsonObject.put("value", "풍선");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "basket");jsonObject.put("value", "바구니");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "bat");jsonObject.put("value", "야구방망이, 박쥐");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "beautiful");jsonObject.put("value", "아름다운");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "bed");jsonObject.put("value", "침대");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "bee");jsonObject.put("value", "벌");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "bell");jsonObject.put("value", "종");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "bicycle(bike)");jsonObject.put("value", "자전거");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "bird");jsonObject.put("value", "새");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "black");jsonObject.put("value", "검은(색)");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "blue");jsonObject.put("value", "파란(색)");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "boy");jsonObject.put("value", "소년");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "break");jsonObject.put("value", "깨뜨리다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "breakfast");jsonObject.put("value", "아침 식사");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "bring");jsonObject.put("value", "가져오다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "brother");jsonObject.put("value", "남자형제, 오빠, 형, 남동생");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "brown");jsonObject.put("value", "갈색(의)");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "brush");jsonObject.put("value", "빗, 빗다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "but");jsonObject.put("value", "그러나");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "candy");jsonObject.put("value", "사탕");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "chalk");jsonObject.put("value", "분필");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "class");jsonObject.put("value", "학급");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "clock");jsonObject.put("value", "시계");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "clothes");jsonObject.put("value", "옷");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "cloud");jsonObject.put("value", "구름");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "color");jsonObject.put("value", "색깔");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "count");jsonObject.put("value", "세다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "cute");jsonObject.put("value", "귀여운");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "dark");jsonObject.put("value", "어두운");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "day");jsonObject.put("value", "하루, 낮");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "dinner");jsonObject.put("value", "저녁 식사");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "dirty");jsonObject.put("value", "더러운");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "doll");jsonObject.put("value", "인형");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "early");jsonObject.put("value", "일찍");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "end");jsonObject.put("value", "끝");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "evening");jsonObject.put("value", "저녁");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "father");jsonObject.put("value", "아버지");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "flower");jsonObject.put("value", "꽃");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "friend");jsonObject.put("value", "친구");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "fruit");jsonObject.put("value", "과일");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "girl");jsonObject.put("value", "소녀");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "give");jsonObject.put("value", "주다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "gold");jsonObject.put("value", "금");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "good");jsonObject.put("value", "좋은");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "green");jsonObject.put("value", "녹색(의)");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "hard");jsonObject.put("value", "딱딱한, 어려운");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "he");jsonObject.put("value", "그는, 그가");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "hot");jsonObject.put("value", "더운");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "ill");jsonObject.put("value", "아픈");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "in");jsonObject.put("value", "~안에");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "jacket");jsonObject.put("value", "웃옷, 재킷");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "jeans");jsonObject.put("value", "청바지");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "kind");jsonObject.put("value", "친절한");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "king");jsonObject.put("value", "왕");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "know");jsonObject.put("value", "알다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "lady");jsonObject.put("value", "숙녀");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "lamp");jsonObject.put("value", "등");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "late");jsonObject.put("value", "늦은");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "light");jsonObject.put("value", "전등, 가벼운");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "man");jsonObject.put("value", "사람, 남자");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "market");jsonObject.put("value", "시장");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "meat");jsonObject.put("value", "고기");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "meet");jsonObject.put("value", "만나다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "minute");jsonObject.put("value", "분");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "Miss/Ms.");jsonObject.put("value", "~양(미혼여성)/~씨(미혼,기혼)");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "money");jsonObject.put("value", "돈");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "morming");jsonObject.put("value", "아침");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "mother");jsonObject.put("value", "어머니");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "Mr.");jsonObject.put("value", "~씨(남자)");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "Mrs.");jsonObject.put("value", "~부인");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "much");jsonObject.put("value", "많이");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "new");jsonObject.put("value", "새로운");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "nice");jsonObject.put("value", "좋은");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "noon");jsonObject.put("value", "정오");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "now");jsonObject.put("value", "지금");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "old");jsonObject.put("value", "나이든, 오래된");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "or");jsonObject.put("value", "또는");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "pay");jsonObject.put("value", "지불하다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", " pick");jsonObject.put("value", "꺾다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "play");jsonObject.put("value", "놀다, 경기하다, 연주하다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "pretty");jsonObject.put("value", "예쁜, 귀여운");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "pull");jsonObject.put("value", "끌다, 당기다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "puppy");jsonObject.put("value", "강아지");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "purple");jsonObject.put("value", "자주색(의)");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "push");jsonObject.put("value", "밀다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "queen");jsonObject.put("value", "여왕");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "quiet");jsonObject.put("value", "조용한");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "red");jsonObject.put("value", "빨간");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "rice");jsonObject.put("value", "쌀, 밥");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "rich");jsonObject.put("value", "부유한");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "right");jsonObject.put("value", "오른쪽의, 옳은");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "sad");jsonObject.put("value", "슬픈");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "say");jsonObject.put("value", "말하다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "school");jsonObject.put("value", "학교");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "see");jsonObject.put("value", "보다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "sell");jsonObject.put("value", "팔다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "set");jsonObject.put("value", "한 벌, 세트, 놓다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "she");jsonObject.put("value", "그녀는, 그녀가");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "sick");jsonObject.put("value", "아픈");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "sister");jsonObject.put("value", "여자 형제, 언니, 여동생, 누나");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "slow");jsonObject.put("value", "느린");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "soap");jsonObject.put("value", "비누");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "soccer");jsonObject.put("value", "축구");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "start");jsonObject.put("value", "시작하다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "stop");jsonObject.put("value", "멈추다, 정지시키다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "strong");jsonObject.put("value", "강한");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "sugar");jsonObject.put("value", "설탕");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "supper");jsonObject.put("value", "저녁 식사");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "table");jsonObject.put("value", "탁자");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "teach");jsonObject.put("value", "가르치다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "there");jsonObject.put("value", "거기에");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "tie");jsonObject.put("value", "넥타이, 묶다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "tired");jsonObject.put("value", "피곤한");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "to");jsonObject.put("value", "~으로, ~에");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "train");jsonObject.put("value", "기차");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "very");jsonObject.put("value", "매우, 아주");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "walk");jsonObject.put("value", "걷다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "want");jsonObject.put("value", "원하다, ~하고 싶다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "watch");jsonObject.put("value", "보다, 손목시계");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "wear");jsonObject.put("value", "입다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "white");jsonObject.put("value", "흰(색)");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "who");jsonObject.put("value", "누구");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "woman");jsonObject.put("value", "여자");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "year");jsonObject.put("value", "해, 년");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "yellow");jsonObject.put("value", "노란색");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "young");jsonObject.put("value", "젊은, 어린");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "zebra");jsonObject.put("value", "얼룩말");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "zero");jsonObject.put("value", "(숫자)0");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "zoo");jsonObject.put("value", "동물원");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "age");jsonObject.put("value", "나이");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "album");jsonObject.put("value", "사진첩");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "angry");jsonObject.put("value", "화난");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "birthday");jsonObject.put("value", "생일");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "bus");jsonObject.put("value", "버스");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "card");jsonObject.put("value", "카드");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "cup");jsonObject.put("value", "컵");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "dream");jsonObject.put("value", "꿈, 꿈꾸다");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "gas");jsonObject.put("value", "가스, 기체");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "grass");jsonObject.put("value", "풀");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "hen");jsonObject.put("value", "암탉");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "hose");jsonObject.put("value", "호스");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "ink");jsonObject.put("value", "잉크");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "kid");jsonObject.put("value", "꼬마");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "large");jsonObject.put("value", "커다란");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "lip");jsonObject.put("value", "입술");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "note");jsonObject.put("value", "메모");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "oil");jsonObject.put("value", "기름");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "piano");jsonObject.put("value", "피아노");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "rose");jsonObject.put("value", "장미");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "sand");jsonObject.put("value", "모래");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "size");jsonObject.put("value", "크기");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "stone");jsonObject.put("value", "돌");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "team");jsonObject.put("value", "팀");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "tomato");jsonObject.put("value", "토마토");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "truck");jsonObject.put("value", "트럭");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "video");jsonObject.put("value", "비디오");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "wet");jsonObject.put("value", "젖은");jsonArray.put(jsonObject);
		jsonObject = new JSONObject();jsonObject.put("key", "wing");jsonObject.put("value", "날개");jsonArray.put(jsonObject);

		List<Word> words = new ArrayList<>();
		for(int i=0; i<jsonArray.length(); i++) {
			JSONObject object = (JSONObject) jsonArray.get(i);
			Word word = new Word();
			word.setId(i+1);
			word.setNo(i+1);
			word.setEnglish((String)object.get("key"));
			word.setKorean((String)object.get("value"));
			words.add(word);
		}

		WordUnit wordUnit = new WordUnit();
		wordUnit.setId(UUID.randomUUID());
		wordUnit.setUnitCode("1");
		wordUnit.setUnitName("Word Test - 300");
		wordUnit.setTestDt("20200225");
		wordUnit.getWords().addAll(words);
		wordTestService.addTempWordUnit(wordUnit);
	}

}
