<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="UTF-8">
    <title>wolfdatabase</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="./resources/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="./resources/raty/lib/jquery.raty.css">
    <script src="./resources/jquery.3.3.1.min.js"></script>
    <script src="./resources/bootstrap/bootstrap.min.js"></script>
    <script src="./resources/art-template.js"></script>
    <script src="./resources/raty/lib/jquery.raty.js"></script>
    <style>
        .highlight {
            color: #ff0000 !important;
        }
        a:active{
            text-decoration: none!important;
        }
    </style>
    

    <style>
        .container {
            padding: 0px;
            margin: 0px;
        }

        .row {
            padding: 0px;
            margin: 0px;
        }

        .col- * {
            padding: 0px;
        }
    </style>

    <style>
        *{
            margin: 0;
            padding: 0;
        }
        .search{
            margin: 30px;
            width: 210px;
            height:35px;
            border:1px solid silver ;
        }
        .search_key{
            width: 150px;
            height: 25px;
            border: none;
            margin-top: 5px;
            margin-left: 5px;
        }
        .submit{
            background:none;
            margin-top: 5px;
            width:40px;
            height: 25px;
            border: none;
            /*这个添加背景图即可*/
        }
    </style>
    <script type="text/html" id="tpl">

        <a href="/persondata/{{persondataId}}" style="color: inherit">
            <div class="row mt-2 shop">
            <div class="col-4 mb-2 pr-2">
            <img class="img-fluid" src="{{cover}}">
            </div>
            <div class="col-8  mb-2 pl-0">
            <h5 class="text-truncate">{{personName}}</h5>

        <div class="mb-2 bg-light small  p-2 w-100 text-truncate">{{author}}</div>


        <div class="mb-2 w-100">{{subTitle}}</div>

            <p>
<#--            <span class="stars" data-score="{{evaluationScore}}" title="gorgeous"></span>-->
<#--            <span class="mt-2 ml-2">{{evaluationScore}}</span>-->
<#--            <span class="mt-2 ml-2">{{evaluationQuantity}}已评</span>-->
        </p>
        </div>
        </div>
        </a>

        <hr>
    </script>
    <script>
        $.fn.raty.defaults.path="./resources/raty/lib/images"
        function loadMore(isRest){
            if (isRest == true) {
                $("#shopList").html("");
                $("#nextPage").val(1);
            }
            var nextPage = $("#nextPage").val();
            var categoryId = $("#categoryId").val();
            var order = $("#order").val();
            $.ajax({
                url:"/persondatas",
                data:{p:nextPage,order:order},
                type:"get",
                dataType:"json",
                success:function (json){
                    console.info(json);
                    let list = json.records;
                    for (let i = 0; i < list.length; i++) {
                        var shop=json.records[i];
                        //将数据结合tpl模板生成html
                        var html = template("tpl", shop);
                        console.info(html);
                        $("#persondataList").append(html);
                    }
                    //显示星型组件
                    $(".stars").raty({readOnly: true});

                    if (json.current < json.pages) {
                        $("#nextPage").val(parseInt(json.current) + 1);
                        $("#btnMore").show();
                        $("#divNoMore").hide();
                    }else{
                        $("#btnMore").hide();
                        $("#divNoMore").show();
                    }
                }
            })
        }
        $(function (){
            // $.ajax({
            //     url:"/shops",
            //     data:{p:1},
            //     type:"get",
            //     dataType:"json",
            //     success:function (json){
            //         console.info(json);
            //         let list = json.records;
            //         for (let i = 0; i < list.length; i++) {
            //             var shop=json.records[i];
            //             //将数据结合tpl模板胜场html
            //             var html = template("tpl", shop);
            //             console.info(html);
            //             $("#shopList").append(html);
            //         }
            //         //显示星型组件
            //         $(".stars").raty({readOnly: true});
            //     }
            // })
            loadMore(true)
        })

        //绑定加载更多按钮单击事件
        $(function (){
            $("#btnMore").click(function(){
                loadMore();
            })

            $(".category").click(function (){
                $(".category").removeClass("highlight");
                $(".category").addClass("text-black-50");
                $(this).addClass("highlight")
                var categoryId=$(this).data("category");
                $("#categoryId").val(categoryId);
                loadMore(true);
            })

            $(".order").click(function (){
                $(".order").removeClass("highlight");
                $(".order").addClass("text-black-50");
                $(this).addClass("highlight")
                var order=$(this).data("order");
                $("#order").val(order);
                loadMore(true);
            })
        })
    </script>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-light bg-white shadow mr-auto">
        <ul class="nav">
            <li class="nav-item">
                <a href="/">
                    <img src="/images/shangbiao.jpg" class="mt-1" style="width: 150px">
                </a>
            </li>

        </ul>

<#--        <form action="/persondata/{}" method="post">-->
<#--            <div class="search">-->
<#--                <input type="text" class="search_key"/>-->
<#--                <input type="submit" class="submit" value="搜索">-->
<#--            </div>-->
<#--        </form>-->

        <a href="/search.html">搜索人物</a>
        <a href="/register.html">注册</a>
        <#if loginMember??>
            <a href="/persondata.html">新增人物</a>
            <a href="/user/logout">注销</a>
        </#if>

        <#if loginMember??>
                <h6 class="mt-1">
                    <img style="width: 2rem;margin-top: -5px" class="mr-1" src="./images/user_icon.png">${loginMember.username}
                </h6>
            <#else>
                <a href="/login.html" class="btn btn-light btn-sm">
                    <img style="width: 2rem;margin-top: -5px" class="mr-1" src="./images/user_icon.png">登录
                </a>
        </#if>





    </nav>
    <div class="row mt-2">


        <div class="col-8 mt-2">
            <h4>热榜用户</h4>
        </div>

<#--        <div class="col-8 mt-2">-->
<#--                <span data-category="-1" style="cursor: pointer" class="highlight  font-weight-bold category">全部</span>-->
<#--                |-->
<#--                    <#list categoryList as category>-->
<#--                    <a style="cursor: pointer" data-category="${category.categoryId}" class="text-black-50 font-weight-bold category">${category.categoryName}</a>-->
<#--                    <#if category_has_next>|</#if>-->
<#--                    </#list>-->
<#--        </div>-->

<#--        <div class="col-8 mt-2">-->
<#--            <span data-order="quantity" style="cursor: pointer" class="order highlight  font-weight-bold mr-3">热度最高</span>-->

<#--            <span data-order="score" style="cursor: pointer" class="order text-black-50 mr-3 font-weight-bold">评分最高</span>-->
<#--        </div>-->
    </div>
    <div class="d-none">
        <input type="hidden" id="nextPage" value="2">
        <input type="hidden" id="categoryId" value="-1">
        <input type="hidden" id="order" value="quantity">
    </div>

    <div id="persondataList">
    

    </div>
    <button type="button" id="btnMore" data-next-page="1" class="mb-5 btn btn-outline-primary btn-lg btn-block">
        点击加载更多...
    </button>
    <div id="divNoMore" class="text-center text-black-50 mb-5" style="display: none;">没有其他数据了</div>
</div>

</body></html>