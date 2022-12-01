let links = null; //Создаём переменную, в которой будут храниться ссылки

let loaded = true; //Переменная, которая обозначает, загрузилась ли страница

let data = //Данные о странице
    {
        title: "",
        body: "",
        link: ""
    };

let page = //Элементы, текст в которых будет меняться
    {
        title: document.getElementById("title"),
        body: document.getElementById("body")
    };

//По умолчанию в макете содержится контент для главной страницы.
//Но если пользователь перейдёт по ссылке, которая ведёт на какую-нибудь статью, он увидит не то, что ожидает.
//Поэтому нужно проверить, на какой странице находится пользователь, и загрузить релевантные данные.
onLoad();

function onLoad() {
    let link = window.location.pathname; //Ссылка страницы без домена

    //У меня сайт находится по ссылке http://localhost/spa, поэтому мне нужно обрезать часть с spa/
    let href = link.replace("spa/", "");

    linkClick(href);
}

function initLinks() {
    links = document.getElementsByClassName("link_internal"); //Находим все ссылки на странице

    for (let i = 0; i < links.length; i++) {
        //Отключаем событие по умолчанию и вызываем функцию LinkClick
        links[i].addEventListener("click", function(e) {
            e.preventDefault();
            linkClick(e.target.getAttribute("href"));
            return false;
        });
    }
}

function linkClick(href) {
    let props = href.split("/"); //Получаем параметры из ссылки. 1 - раздел, 2 - идентификатор
    switch (props[1]) {
        case "main":
            sendRequest("?page=main", href); //Отправляем запрос на сервер
            break;

        case "articles":
            if (props.length == 3 && !isNaN(props[2]) && Number(props[2]) > 0) //Проверяем валидность идентификатора и тоже отправляем запрос
            {
                sendRequest("?page=articles&id=" + props[2], href);
            }
            break;
    }
}

function sendRequest(query, link) {
    let xhr = new XMLHttpRequest(); //Создаём объект для отправки запроса

    xhr.open("GET", "/core" + query, true); //Открываем соединение

    xhr.onreadystatechange = function() //Указываем, что делать, когда будет получен ответ от сервера
        {
            if (xhr.readyState != 4) return; //Если это не тот ответ, который нам нужен, ничего не делаем

            //Иначе говорим, что сайт загрузился
            loaded = true;

            if (xhr.status == 200) //Если ошибок нет, то получаем данные
            {
                getData(JSON.parse(xhr.responseText), link);
            } else //Иначе выводим сообщение об ошибке
            {
                alert("Loading error! Try again later.");
                console.log(xhr.status + ": " + xhr.statusText);
            }
        }

    loaded = false; //Говорим, что идёт загрузка

    //Устанавливаем таймер, который покажет сообщение о загрузке, если она не завершится через 2 секунды
    setTimeout(showLoading, 2000);
    xhr.send(); //Отправляем запрос
}

function getData(response, link) //Получаем данные
{
    data = {
        title: response.title,
        body: response.body,
        link: link
    };

    updatePage(); //Обновляем контент на странице
}

function showLoading() {
    if (!loaded) //Если страница ещё не загрузилась, то выводим сообщение о загрузке
    {
        page.body.innerHTML = "Loading...";
    }
}

function updatePage() //Обновление контента
{
    page.title.innerText = data.title;
    page.body.innerHTML = data.body;

    document.title = data.title;
    window.history.pushState(data.body, data.title, data.link); //Меняем ссылку

    initLinks(); //Инициализируем новые ссылки
}