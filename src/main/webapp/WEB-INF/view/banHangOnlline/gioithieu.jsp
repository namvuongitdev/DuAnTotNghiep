<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700&subset=vietnamese" rel="stylesheet">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>Document</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        ul {
            list-style-type: none;
        }

        a,
        a:hover {
            text-decoration: none;
        }


        body .testimonial {
            padding: 100px 0;
        }
        body .testimonial .row .tabs {
            all: unset;
            margin-right: 50px;
            display: flex;
            flex-direction: column;
        }
        body .testimonial .row .tabs li {
            all: unset;
            display: block;
            position: relative;
        }
        body .testimonial .row .tabs li.active::before {
            position: absolute;
            content: "";
            width: 50px;
            height: 50px;
            background-color: #71b85f;
            border-radius: 50%;
        }
        body .testimonial .row .tabs li.active::after {
            position: absolute;
            content: "";
            width: 30px;
            height: 30px;
            background-color: #71b85f;
            border-radius: 50%;
        }
        body .testimonial .row .tabs li:nth-child(1) {
            align-self: flex-end;
        }
        body .testimonial .row .tabs li:nth-child(1)::before {
            left: 64%;
            bottom: -50px;
        }
        body .testimonial .row .tabs li:nth-child(1)::after {
            left: 97%;
            bottom: -81px;
        }
        body .testimonial .row .tabs li:nth-child(1) figure img {
            margin-left: auto;
        }
        body .testimonial .row .tabs li:nth-child(2) {
            align-self: flex-start;
        }
        body .testimonial .row .tabs li:nth-child(2)::before {
            right: -65px;
            top: 50%;
        }
        body .testimonial .row .tabs li:nth-child(2)::after {
            bottom: 101px;
            border-radius: 50%;
            right: -120px;
        }
        body .testimonial .row .tabs li:nth-child(2) figure img {
            margin-right: auto;
            max-width: 300px;
            width: 100%;
            margin-top: -50px;
        }
        body .testimonial .row .tabs li:nth-child(3) {
            align-self: flex-end;
        }
        body .testimonial .row .tabs li:nth-child(3)::before {
            right: -10px;
            top: -66%;
        }
        body .testimonial .row .tabs li:nth-child(3)::after {
            top: -130px;
            border-radius: 50%;
            right: -46px;
        }
        body .testimonial .row .tabs li:nth-child(3) figure img {
            margin-left: auto;
            margin-top: -50px;
        }
        body .testimonial .row .tabs li:nth-child(3):focus {
            border: 10px solid red;
        }
        body .testimonial .row .tabs li figure {
            position: relative;
        }
        body .testimonial .row .tabs li figure img {
            display: block;
        }
        body .testimonial .row .tabs li figure::after {
            content: "";
            position: absolute;
            top: 0;
            z-index: -1;
            width: 100%;
            height: 100%;
            border: 4px solid #dff9d9;
            border-radius: 50%;
            -webkit-transform: scale(1);
            -ms-transform: scale(1);
            transform: scale(1);
            -webkit-transition: 0.3s;
            -o-transition: 0.3s;
            transition: 0.3s;
        }

        body .testimonial .row .carousel > h3 {
            font-size: 20px;
            line-height: 1.45;
            color: rgba(0, 0, 0, 0.5);
            font-weight: 600;
            margin-bottom: 0;
        }
        body .testimonial .row .carousel h1 {
            font-size: 40px;
            line-height: 1.225;
            margin-top: 23px;
            font-weight: 700;
            margin-bottom: 0;
        }
        body .testimonial .row .carousel .carousel-indicators {
            padding-top: 43px;
            display: flex;
            list-style: none;
        }
        body .testimonial .row .carousel .carousel-indicators li {
            background: #000;
            background-clip: padding-box;
            height: 2px;
        }
        body .testimonial .row .carousel .carousel-inner .carousel-item .quote-wrapper {
            margin-top: 42px;
        }
        body .testimonial .row .carousel .carousel-inner .carousel-item .quote-wrapper p {
            font-size: 18px;
            line-height: 1.72222;
            font-weight: 500;
            color: rgba(0, 0, 0, 0.7);
        }
        body .testimonial .row .carousel .carousel-inner .carousel-item .quote-wrapper h3 {
            font-family: 'Montserrat', sans-serif;
            color: #000;
            font-weight: 700;
            margin-top: 37px;
            font-size: 20px;
            line-height: 1.45;
        }
        @media only screen and (max-width: 900px) {
            body .testimonial .row .tabs {
                margin-right: 25px;
            }
        }

    </style>
</head>
<body>
<section class="testimonial">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 d-none d-lg-block">
                <ol class="carousel-indicators tabs">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active">
                        <figure>
                            <img src="/anh/gioithieu2.jpg" style="width: 179px;height: 185px;border-radius: 50%;" class="img-fluid" alt="">
                        </figure>
                    </li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1">
                        <figure>
                            <img src="/anh/gioithieu4.jpg" style="width: 306px;height: 306px;border-radius: 50%;" class="img-fluid" alt="">
                        </figure>
                    </li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2">
                        <figure>
                            <img src="/anh/gioithieu3.jpg" style="width: 179px;height: 185px;border-radius: 50%;" class="img-fluid" alt="">
                        </figure>
                    </li>
                </ol>
            </div>
            <div class="col-lg-6 d-flex justify-content-center align-items-center">
                <div id="carouselExampleIndicators" data-interval="false" class="carousel slide" data-ride="carousel">
                    <h3 style="color: #7a7a7a">INTRODUCE</h3>
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <div class="quote-wrapper">
                                <p>Men's fashion brand Sports Clothing was born with the desire to accompany, inspire and encourage young men to dare to step out of their comfort zone to freely and confidently express themselves in a style that suits their needs. self. That's why the Sports Clothing men's fashion system invests enthusiastically in researching and designing each product in detail to bring new experiences to customers, which is also a message to young people to give themselves. experience yourself, dare to change, make breakthroughs to rise up. We only truly change when we take action. Sports Clothing believes that whether you can succeed or fail, it is definitely only experiences that help you grow. Growing up is a journey with youthful milestones, so that when you and I look back, we can confidently not regret "if only..."</p>
                            </div>
                        </div>

                    </div>

                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>

