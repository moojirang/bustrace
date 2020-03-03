<%@ page import="java.util.*" %>
<%@ page import="com.dazzilove.dd.domain.*" %>
<%
    WordUnit wordUnit = (WordUnit) request.getAttribute("wordUnit");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
    <head>
        <link href="/dd/css/wordTest.css" rel="stylesheet"/>
        <style>
            .btn-area-exist-exam { display: none; }
        </style>
        <script src="/dd/js/jquery-3.4.1.min.js"></script>
        <script>
            var words;
            var wordUnitExamSeq;
        </script>
        <script>
            function createQuestion() {
                $.ajax({
                    method: "GET",
                    url: "/dd/getNewExamInfo",
                    data: {
                        "id" : "<%= wordUnit.getId() %>",
                        "suffleYn": document.getElementById("suffleYn").value,
                        "suffleEngKor": document.getElementById("suffleEngKor").value
                    }
                })
                .done(function(data) {
                    wordUnitExamSeq = data.wordUnitExamSeq;

                    var wordList = document.getElementById("words");
                    if (wordList != null) {
                        wordList.remove();
                    }

                    var list = document.getElementById("list");

                    var ul = document.createElement("ul");
                    ul.setAttribute("id", "words");
                    ul.setAttribute("class", "word-list");
                    list.appendChild(ul);

                    printCreateDt();

                    document.getElementsByClassName("btn-area-exist-exam")[0].style.display = "block";

                    createTitleRow(ul);
                    createDataRows(ul, data);
                })
                .fail(function() {
                    alert("error");
                });
            }
            
            function saveQuestion() {
                var examWords = {"words": words};
                $.ajax({
                    method: "POST",
                    url: "/dd/saveWordUnitExam",
                    data: {
                        "id" : "<%= wordUnit.getId() %>",
                        "wordUnitExamSeq": wordUnitExamSeq,
                        "examWords" : JSON.stringify(examWords)
                    }
                })
                .done(function(msg) {
                    alert(msg);
                })
                .fail(function() {
                    alert("error");
                });
            }

            function hiddenAnswer() {
                var answers = document.getElementsByClassName("word-answer-span");
                for(i=0; i<answers.length; i++) {
                    answers[i].style.display = "none";
                }
            }

            function showAnswer() {
                var answers = document.getElementsByClassName("word-answer-span");
                for(i=0; i<answers.length; i++) {
                    answers[i].style.display = "block";
                }
            }

            function printCreateDt() {
                var nowDt = new Date();
                var nowDtStr = "";
                nowDtStr += nowDt.getFullYear();
                nowDtStr += "/" + (nowDt.getMonth() + 1);
                nowDtStr += "/" + nowDt.getDay();
                nowDtStr += " " + nowDt.getHours();
                nowDtStr += ":" + nowDt.getMinutes();
                nowDtStr += ":" + nowDt.getSeconds();
                var createDt = document.getElementById("createDt");
                createDt.innerHTML = "No " + wordUnitExamSeq + ", Created At " + nowDtStr;
            }

            function createTitleRow(ul) {
                var li = document.createElement("li");
                li.setAttribute("class", "word-item");

                var no1 = document.createElement("div");
                no1.appendChild(document.createTextNode("No"));
                no1.setAttribute("class", "word-no word-title word-height");

                var no2 = document.createElement("div");
                no2.appendChild(document.createTextNode("No"));
                no2.setAttribute("class", "word-no word-title word-height");

                var question1 = document.createElement("div");
                question1.appendChild(document.createTextNode("Question"));
                question1.setAttribute("class", "word-question word-title word-height");

                var question2 = document.createElement("div");
                question2.appendChild(document.createTextNode("Question"));
                question2.setAttribute("class", "word-question word-title word-height");

                var answer1 = document.createElement("div");
                answer1.appendChild(document.createTextNode("Answer"));
                answer1.setAttribute("class", "word-answer word-title word-height");

                var answer2 = document.createElement("div");
                answer2.appendChild(document.createTextNode("Answer"));
                answer2.setAttribute("class", "word-answer word-title word-height last-col");

                li.appendChild(no1);
                li.appendChild(question1);
                li.appendChild(answer1);
                li.appendChild(no2);
                li.appendChild(question2);
                li.appendChild(answer2);

                ul.append(li);
            }

            function createDataRows(ul, data) {
                var suffledWords = data.words;
                words = suffledWords;

                var tempLi;
                for(i=0; i<suffledWords.length; i++) {
                    var first = (i%2==0) ? true : false;
                    var word = suffledWords[i];

                    if (first) {
                        var li = document.createElement("li");
                        li.setAttribute("class", "word-item");
                        tempLi = li;

                        ul.appendChild(li);
                    }

                    var noValue = i+1;
                    var no = document.createElement("div");
                    no.appendChild(document.createTextNode(noValue));
                    no.setAttribute("class", "word-no word-height");

                    var question = document.createElement("div");
                    question.setAttribute("no", noValue);
                    question.setAttribute("english", word.english);
                    question.setAttribute("korean", word.korean);
                    question.setAttribute("question", word.question);
                    question.setAttribute("answer", word.answer);
                    question.appendChild(document.createTextNode(word.question));
                    question.setAttribute("class", "word-question word-height");

                    var answerSpan = document.createElement("span");
                    answerSpan.setAttribute("class", "word-answer-span");
                    answerSpan.appendChild(document.createTextNode(word.answer));

                    var answer = document.createElement("div");
                    answer.appendChild(answerSpan);
                    if (first) {
                        answer.setAttribute("class", "word-answer word-height");
                    } else {
                        answer.setAttribute("class", "word-answer word-height last-col");
                    }

                    tempLi.appendChild(no);
                    tempLi.appendChild(question);
                    tempLi.appendChild(answer);
                }
            }

            function getSuffledWords(list) {
                var suffleEngKor = document.getElementById("suffleEngKor").value;
                var j, x, i;
                var a = [];
                for (i=0; i<list.length; i++) {
                    a.push(list[i]);
                }
                for (i = a.length; i; i -= 1) {
                    j = Math.floor(Math.random() * i);
                    x = a[i - 1];
                    a[i - 1] = a[j];
                    a[j] = x;

                    if(j % suffleEngKor == 0) {
                        x.question = x.korean;
                        x.answer = x.english;
                    }
                }
                return a;
            }
        </script>
    </head>
    <body>
        <div class="content">
            <div class="title">
                <span><%= wordUnit.getUnitName() %></span>
            </div>
            <div class="btns">
                <div>
                    <label>
                        Suffle
                        <select id="suffleYn">
                            <option value="Y" selected="selected">Y</option>
                            <option value="N">N</option>
                        </select>
                    </label>
                    <label>Suffle Eng/Kor
                        <select id="suffleEngKor">
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10" selected="selected">10</option>
                        </select>
                    </label>
                    <button class="btn" text="Create" onclick="createQuestion()">Create Questions</button>
                </div>
                <div class="btn-area-exist-exam">
                    <div>
                        <button class="btn" text="Create" onclick="hiddenAnswer()">Hidden Answers</button>
                        <button class="btn" text="Create" onclick="showAnswer()">Show Answers</button>
                    </div>
                    <div>
                        <span id="createDt" style="font-weight: bold"></span>
                        <button class="btn" text="Create" onclick="saveQuestion()">Save</button>
                    </div>
                </div>
            </div>
            <div class="list" id="list"></div>
            <div style="clear: both; padding-top: 30px;"></div>
        </div>
    </body>
</html>