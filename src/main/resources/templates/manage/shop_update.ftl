<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <!-- Meta, title, CSS, favicons, etc. -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>修改商家 | </title>

  <!-- Bootstrap -->
  <link href="../../static/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Font Awesome -->
  <link href="../../static/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <!-- NProgress -->
  <link href="../../static/vendors/nprogress/nprogress.css" rel="stylesheet">
  <!-- Dropzone.js -->
  <link href="../../static/vendors/dropzone/dist/min/dropzone.min.css" rel="stylesheet">

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
            <h3>商店信息 <small><a href="/os/manage/booth/list"><i class="fa fa-angle-double-right"></i> 返回列表 <i class="fa fa-angle-double-left"></i></a></small></h3>
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
                <h2>修改信息
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
                <form id="form_info" class="form-horizontal form-label-left" novalidate action="/os/manage/booth/update"
                      method="post">

                  <span class="section">店主信息</span>

                  <div class="item form-group hidden">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bId">bId
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input id="bId" class="form-control col-md-7 col-xs-12" name="bId" type="text"
                             value="${boothVO.getBId()!}">
                    </div>
                  </div>
                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bOwnerName">店主姓名 <span
                        class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="text" id="bOwnerName" name="bOwnerName" data-validate-length-range="1,15"
                             class="form-control col-md-7 col-xs-12" required="required"
                             value="${boothVO.getBOwnerName()!}" placeholder="长度:1-15">
                    </div>
                  </div>
                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bOwnerEmail">邮箱 <span
                        class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="email" id="bOwnerEmail" name="bOwnerEmail" required="required"
                             class="form-control col-md-7 col-xs-12" value="${boothVO.getBOwnerEmail()!}">
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
                             value="${boothVO.getBOwnerPhone()!}" placeholder="输入11位手机号">
                    </div>
                  </div>


                  <span class="section">商店信息</span>
                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bName">店名 <span
                        class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="text" id="bName" name="bName" data-validate-length-range="3,15"
                             class="form-control col-md-7 col-xs-12" required="required" value="${boothVO.getBName()!}"
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
                             class="form-control col-md-7 col-xs-12" value="${boothVO.getBCanteen()!}"
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
                             class="form-control col-md-7 col-xs-12" value="${boothVO.getBWindow()!}"
                             placeholder='格式:"窗口XX"'>
                    </div>
                  </div>
                  <div class="item form-group hidden">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="quyu">区域 <span
                        class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="text" id="quyu" name="quyu" required="required"
                             class="form-control col-md-7 col-xs-12" value="${boothVO.getQuyu()!}">
                    </div>
                  </div>

                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bQuyu">区域选择 <span
                        class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <div class="btn-group" data-toggle="buttons">
                        <label class="btn btn-default <#if boothVO.getBQuyu() == 1>active</#if>">
                          <input type="radio" name="bQuyu" id="quyu_option1" value="1"> 东区
                        </label>
                        <label class="btn btn-default <#if boothVO.getBQuyu() == 2>active</#if>">
                          <input type="radio" name="bQuyu" id="quyu_option2" value="2"> 西区
                        </label>
                      <#--<label class="btn btn-default">-->
                      <#--<input type="radio" name="bQuyu" id="option3"> Option 3-->
                      <#--</label>-->
                      </div>
                    <#--<input type="number" id="bQuyu" name="bQuyu" required="required" data-validate-minmax="0,2"-->
                    <#--class="form-control col-md-7 col-xs-12" value="${boothVO.getBQuyu()!}">-->
                    </div>
                  </div>

                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bComment">备注
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                    <textarea id="bComment" name="bComment"
                              class="form-control col-md-7 col-xs-12">${boothVO.getBComment()!}</textarea>
                    </div>
                  </div>
                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bCreateTime">创建时间
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="text" id="bCreateTime" name="bCreateTime_" readonly="readonly"
                             class="form-control col-md-7 col-xs-12" value="${boothVO.getBCreateTime()!}">
                    </div>
                  </div>
                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bUpdateTime">上一次更新时间
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="text" id="bUpdateTime" name="bUpdateTime_" readonly="readonly"
                             class="form-control col-md-7 col-xs-12" value="${boothVO.getBUpdateTime()!}">
                    </div>
                  </div>

                  <div class="ln_solid"></div>
                  <div class="form-group">
                    <div class="col-md-6 col-md-offset-3">
                      <button id="submit_info" type="submit" class="btn btn-success">修改</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>


            <div class="x_panel">
              <div class="x_title">
                <h2>修改密码
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
                <form id="form_password" class="form-horizontal form-label-left" novalidate
                      action="/os/manage/booth/updatePassword"
                      method="post">
                <#--<span class="section">修改密码</span>-->
                  <div class="item form-group hidden">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bId2">bId2
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input id="bId2" class="form-control col-md-7 col-xs-12" name="bId" type="text"
                             value="${boothVO.getBId()!}">
                    </div>
                  </div>

                  <div class="item form-group">
                    <label for="password" class="control-label col-md-3 col-sm-3 col-xs-12">更改密码
                      <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input id="bOwnerPassword" type="password" name="bOwnerPassword" data-validate-length-range="6,12"
                             class="form-control col-md-7 col-xs-12" required="required" placeholder="长度为6-12">
                    </div>
                  </div>
                  <div class="item form-group">
                    <label for="password2" class="control-label col-md-3 col-sm-3 col-xs-12">确认新密码
                      <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input id="password2" type="password" name="password2" data-validate-length-range="6,12"
                             data-validate-linked="bOwnerPassword" class="form-control col-md-7 col-xs-12"
                             required="required" placeholder="长度为6-12">
                    </div>
                  </div>

                  <div class="ln_solid"></div>
                  <div class="form-group">
                    <div class="col-md-6 col-md-offset-3">
                      <button id="submit_password" type="submit" class="btn btn-success">修改</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>


            <div class="x_panel">
              <div class="x_title">
                <h2>修改图片
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
                <p>改动可能需要几分钟生效。</p>
                <div class="row">
                <#--<div class="col-md-4 col-sm-6 col-xs-12"></div>-->
                <#--<div class="control-label col-md-3 col-sm-3 col-xs-12">-->
                <#--<form action="/os/res/addBImg" class="dropzone">-->
                <#--<input name="bid" class="hidden" value="${boothVO.getBId()}">-->
                <#--<div class="fallback">-->
                <#--<input name="file" type="file"/>-->
                <#--</div>-->
                <#--</form>-->
                <#--</div>-->
                <#--</div>-->

                  <form id="form_img" class="form-horizontal form-label-left" novalidate
                        action="/os/manage/booth/updateImg" enctype="multipart/form-data"
                        method="post">
                  <#--<span class="section">修改图片</span>-->
                    <div class="item form-group hidden">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bId3">bId3
                      </label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                        <input id="bId3" class="form-control col-md-7 col-xs-12" name="bId" type="text"
                               value="${boothVO.getBId()!}">
                      </div>
                    </div>

                    <div class="item form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12">目前图片
                      </label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                        <img src="${boothVO.getBImg()}" alt="..." class="img-thumbnail"
                             style="height: 100px; width: 100px;">
                      </div>
                    </div>

                    <div class="item form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="file">选择图片
                        <span class="required">*</span>
                      </label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="file" id="file" name="file"
                               class="form-control col-md-7 col-xs-12" required="required">
                      </div>
                    </div>

                    <div class="item form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bImg">图片URL
                        <span class="required">*</span>
                      </label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="text" id="bImg" name="bImg" readonly="readonly"
                               class="form-control col-md-7 col-xs-12" value="${boothVO.getBImg()!}">
                      </div>
                    </div>
                    <div class="ln_solid"></div>
                    <div class="form-group">
                      <div class="col-md-6 col-md-offset-3">
                        <button id="submit_img" type="submit" class="btn btn-success">修改</button>
                      </div>
                    </div>
                  </form>
                </div>
              </div>
            </div>


              <div class="x_panel">
                <div class="x_title">
                  <h2>修改状态
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

                  <div class="alert alert-warning alert-dismissible fade in" role="alert">
                    状态若修改成“关闭”，则商户无法自行开启 <strong>（相当于封户）</strong>，只有管理员权限可以重新打开。
                  </div>

                  <p></p>
                  <form id="form_state" class="form-horizontal form-label-left" novalidate
                        action="/os/manage/booth/updateState"
                        method="post">
                  <#--<span class="section">修改状态</span>-->
                    <div class="item form-group hidden">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bId4">bId4
                      </label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                        <input id="bId4" class="form-control col-md-7 col-xs-12" name="bId" type="text"
                               value="${boothVO.getBId()!}">
                      </div>
                    </div>
                    <div class="item form-group hidden">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="state">状态 <span
                          class="required">*</span>
                      </label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="text" id="state" name="state" required="required" data-validate-minmax="1,5"
                               class="form-control col-md-7 col-xs-12" value="${boothVO.getState()!}">
                      </div>
                    </div>
                    <div class="item form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bState">状态 <span
                          class="required">*</span>
                      </label>
                      <div class="col-md-6 col-sm-6 col-xs-12">

                        <div class="btn-group" data-toggle="buttons">
                          <label class="btn btn-default <#if boothVO.getBState() == 0>active</#if>">
                            <input type="radio" name="bState" id="state_option1" value="0"> 营业
                          </label>
                          <label class="btn btn-default <#if boothVO.getBState() == 1>active</#if>">
                            <input type="radio" name="bState" id="state_option2" value="1"> 休息
                          </label>
                          <label class="btn btn-danger <#if boothVO.getBState() == 2>active</#if>"">
                            <input type="radio" name="bState" id="state_option3" value="2"> 关闭
                          </label>
                        </div>
                        <#--<input type="number" id="bState" name="bState" required="required"-->
                               <#--class="form-control col-md-7 col-xs-12" value="${boothVO.getBState()!}">-->
                      </div>
                    </div>

                    <div class="ln_solid"></div>
                    <div class="form-group">
                      <div class="col-md-6 col-md-offset-3">
                        <button id="submit_state" type="submit" class="btn btn-success">修改</button>
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
  <!-- Dropzone.js -->
  <script src="../../static/vendors/dropzone/dist/min/dropzone.min.js"></script>
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
              $("#form_password")
                  .on("blur", "input[required], input.optional, select.required", validator.checkField)
                  .on("change", "select.required", validator.checkField)
                  .on("keypress", "input[required][pattern]", validator.keypress),
              $("#form_img")
                  .on("blur", "input[required], input.optional, select.required", validator.checkField)
                  .on("change", "select.required", validator.checkField)
                  .on("keypress", "input[required][pattern]", validator.keypress),
              $("#form_state")
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
              $("#form_password").submit(function (a) {
                a.preventDefault();
                var b = !0;
                return validator.checkAll($(this)) || (b = !1), b && this.submit(), !1
              }),
              $("#form_img").submit(function (a) {
                a.preventDefault();
                var b = !0;
                return validator.checkAll($(this)) || (b = !1), b && this.submit(), !1
              }),
              $("#form_state").submit(function (a) {
                a.preventDefault();
                var b = !0;
                return validator.checkAll($(this)) || (b = !1), b && this.submit(), !1
              })
      )
    }
  </script>
</body>
</html>