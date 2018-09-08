<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <!-- Meta, title, CSS, favicons, etc. -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>新增订单 | </title>

  <!-- Bootstrap -->
  <link href="../../static/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Font Awesome -->
  <link href="../../static/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <!-- NProgress -->
  <link href="../../static/vendors/nprogress/nprogress.css" rel="stylesheet">

  <!-- Custom Theme Style -->
  <link href="../../static/build/css/custom.min.css" rel="stylesheet">
</head>

<body class="nav-md">
<div class="container body">
  <div class="main_container">
    <#include "../template/sidebar.html">
    <#include "../template/top.html">

    <!-- page content -->
    <div class="right_col" role="main">
      <div class="">
        <div class="page-title">
          <div class="title_left">
            <h3>新增订单
              <small><a href="/os/"><i class="fa fa-angle-double-right"></i> 返回主页 <i
                  class="fa fa-angle-double-left"></i></a></small>
            </h3>
          </div>

          <div class="title_right">
            <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
              <div class="input-group">
                <input type="text" class="form-control" placeholder="Search for...">
                <span class="input-group-btn">
                              <button class="btn btn-default" type="button">Go!</button>
                          </span>
              </div>
            </div>
          </div>
        </div>
        <div class="clearfix"></div>

        <div class="row">
          <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
              <div class="x_title">
                <h2>填写信息
                <#--<small>修改信息</small>-->
                </h2>
                <ul class="nav navbar-right panel_toolbox">
                  <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                  </li>
                  <!--<li class="dropdown">-->
                  <!--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>-->
                  <!--<ul class="dropdown-menu" role="menu">-->
                  <!--<li><a href="#">Settings 1</a>-->
                  <!--</li>-->
                  <!--<li><a href="#">Settings 2</a>-->
                  <!--</li>-->
                  <!--</ul>-->
                  <!--</li>-->
                  <!--<li><a class="close-link"><i class="fa fa-close"></i></a>-->
                  <!--</li>-->
                </ul>
                <div class="clearfix"></div>
              </div>
              <div class="x_content">
                <p></p>
                <form id="form_info" class="form-horizontal form-label-left" novalidate action="/os/user/order/create"
                      method="post">

                  <span class="section">商品信息</span>

                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="items">items
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input id="items" class="form-control col-md-7 col-xs-12" name="items" type="text"
                             value="[{'id':'1','number':15},{'id':'3','number':10}]">
                    </div>
                  </div>

                  <span class="section">地址信息</span>
                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="aid">aid <span
                        class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="text" id="aid" name="aid"
                             class="form-control col-md-7 col-xs-12" value="1">
                    </div>
                  </div>

                  <span class="section">下单人信息</span>
                  <div class="item form-group">
                    <label for="sid" class="control-label col-md-3 col-sm-3 col-xs-12">sid
                      <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input id="sid" type="text" name="sid"
                             class="form-control col-md-7 col-xs-12" required="required" value="1">
                    </div>
                  </div>

                  <div class="ln_solid"></div>
                  <div class="form-group">
                    <div class="col-md-6 col-md-offset-3">
                      <button id="submit_All" type="submit" class="btn btn-success">提交</button>
                    </div>
                  </div>
                </form>


              </div>
            </div>

          </div>
        </div>
      </div>
    </div>
    <!-- /page content -->

    <!-- footer content -->
    <#include "../template/foot.html">

  </div>
</div>

<!-- jQuery -->
<script src="../../static/vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="../../static/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="../../static/vendors/fastclick/lib/fastclick.js"></script>
<!-- NProgress -->
<script src="../../static/vendors/nprogress/nprogress.js"></script>
<!-- validator -->
<script src="../../static/vendors/validator/validator.js"></script>

<!-- Custom Theme Scripts -->
<script src="../../static/build/js/custom.min.js"></script>
<script>
  function init_validator() {
    "undefined" != typeof validator && (
        console.log("init_validator"),
            $("#form_info")
                .on("blur", "input[required], input.optional, select.required", validator.checkField)
                .on("change", "select.required", validator.checkField)
                .on("keypress", "input[required][pattern]", validator.keypress),
            $("#form_img")
                .on("blur", "input[required], input.optional, select.required", validator.checkField)
                .on("change", "select.required", validator.checkField)
                .on("keypress", "input[required][pattern]", validator.keypress),
            $(".multi.required").on("keyup blur", "input", function () {
              validator.checkField.apply($(this).siblings().last()[0])
            }),
            $("#form_info").submit(function (a) {
              a.preventDefault();
              var b = !0;
              return validator.checkAll($(this)) || (b = !1), b && this.submit(), !1
            }),
            $("#form_img").submit(function (a) {
              a.preventDefault();
              var b = !0;
              return validator.checkAll($(this)) || (b = !1), b && this.submit(), !1
            })
    )
  }
</script>
</body>
</html>