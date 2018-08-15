<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <!-- Meta, title, CSS, favicons, etc. -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>新增商家 | </title>

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
            <h3>新增商家
              <small><a href="/os/"><i class="fa fa-angle-double-right"></i> 返回主页 <i class="fa fa-angle-double-left"></i></a></small>
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
                <form id="form_info" class="form-horizontal form-label-left" novalidate action="/os/manage/booth/add"
                      method="post">

                  <span class="section">店主信息</span>

                  <div class="item form-group hidden">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bId">bId
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input id="bId" class="form-control col-md-7 col-xs-12" name="bId" type="text"
                             value="">
                    </div>
                  </div>
                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bOwnerName">店主姓名 <span
                        class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="text" id="bOwnerName" name="bOwnerName" data-validate-length-range="1,15"
                             class="form-control col-md-7 col-xs-12" required="required"
                             value="" placeholder="长度:1-15">
                    </div>
                  </div>
                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bOwnerEmail">邮箱 <span
                        class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="email" id="bOwnerEmail" name="bOwnerEmail" required="required"
                             class="form-control col-md-7 col-xs-12" value="">
                    </div>
                  </div>
                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bOwnerPhone">电话 <span
                        class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="tel" id="bOwnerPhone" name="bOwnerPhone"
                             required="required"
                             pattern="phone" class="form-control col-md-7 col-xs-12"
                             value="" placeholder="输入11位手机号">
                    </div>
                  </div>


                  <span class="section">商店信息</span>
                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bName">店名 <span
                        class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="text" id="bName" name="bName" data-validate-length-range="3,15"
                             class="form-control col-md-7 col-xs-12" required="required" value=""
                             placeholder="长度:3-15">
                    </div>
                  </div>
                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bCanteen">餐厅 <span
                        class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="text" id="bCanteen" name="bCanteen" required="required"
                             data-validate-length-range="1,20"
                             class="form-control col-md-7 col-xs-12" value=""
                             placeholder="长度:1-20">
                    </div>
                  </div>
                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bWindow">窗口号 <span
                        class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="text" id="bWindow" name="bWindow" required="required"
                             data-validate-length-range="1,6"
                             class="form-control col-md-7 col-xs-12" value=""
                             placeholder='格式:"窗口XX"'>
                    </div>
                  </div>

                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="quyu_option2">区域选择 <span
                        class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="number" class="hidden" name="bQuyu" id="bQuyu" value="1"/>
                      <div class="btn-group" data-toggle="buttons">
                        <label class="btn btn-default active">
                          <input type="radio" name="quyu" id="quyu_option1" value="1" onchange='setValue("bQuyu", 1)'> 东区
                        </label>
                        <label class="btn btn-default">
                          <input type="radio" name="quyu" id="quyu_option2" value="2" onchange='setValue("bQuyu", 2)'> 西区
                        </label>
                      </div>
                    </div>
                  </div>

                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bComment">备注
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                    <textarea id="bComment" name="bComment"
                              class="form-control col-md-7 col-xs-12"></textarea>
                    </div>
                  </div>

                <span class="section">设置密码</span>
                  <div class="item form-group">
                    <label for="bOwnerPassword" class="control-label col-md-3 col-sm-3 col-xs-12">设置密码
                      <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input id="bOwnerPassword" type="password" name="bOwnerPassword" data-validate-length-range="6,12"
                             class="form-control col-md-7 col-xs-12" required="required" placeholder="长度为6-12">
                    </div>
                  </div>
                  <div class="item form-group">
                    <label for="password2" class="control-label col-md-3 col-sm-3 col-xs-12">确认密码
                      <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input id="password2" type="password" name="password2" data-validate-length-range="6,12"
                             data-validate-linked="bOwnerPassword" class="form-control col-md-7 col-xs-12"
                             required="required" placeholder="长度为6-12">
                    </div>
                  </div>

                <span class="section">选择状态</span>


                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="state_option3">状态 <span
                        class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="number" class="hidden" id="bState" name="bState" value="0">
                      <div class="btn-group" data-toggle="buttons">
                        <label class="btn btn-default active">
                          <input type="radio" name="state" id="state_option1" value="0" onchange='setValue("bState", 0)'> 营业
                        </label>
                        <label class="btn btn-default">
                          <input type="radio" name="state" id="state_option2" value="1" onchange='setValue("bState", 1)'> 休息
                        </label>
                        <label class="btn btn-danger ">
                        <input type="radio" name="state" id="state_option3" value="2" onchange='setValue("bState", 2)'> 关闭
                        </label>
                      </div>
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



            <div class="x_panel">
              <div class="x_title">
                <h2>图片
                <small>默认</small>
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
                <p>创建后该图片会设置成默认图片，可稍后在商店详情中修改或由商家自行修改。</p>
                <div class="item form-group">
                  <label class="control-label col-md-3 col-sm-3 col-xs-12">默认图片:
                  </label>
                  <div class="col-md-6 col-sm-6 col-xs-12">
                    <img src="/os/static/images/booth-default.png" alt="..." class="img-thumbnail" style="height: 100px; width: 100px;">
                  </div>
                </div>
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