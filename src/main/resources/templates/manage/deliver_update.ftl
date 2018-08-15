<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <!-- Meta, title, CSS, favicons, etc. -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>修改快递员 | </title>

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
            <h3>快递员信息 <small><a href="/os/manage/deliver/list"><i class="fa fa-angle-double-right"></i> 返回列表 <i class="fa fa-angle-double-left"></i></a></small></h3>
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
                <h2>修改信息<#--<small>修改信息</small>--></h2>
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
                <form id="form_info" class="form-horizontal form-label-left" novalidate action="/os/manage/deliver/update"
                      method="post">

                  <span class="section">个人信息</span>

                  <div class="item form-group hidden">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="dId">dId
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input id="dId" class="form-control col-md-7 col-xs-12" name="dId" type="text"
                             value="${delivererVO.getDId()!}">
                    </div>
                  </div>
                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="dName">姓名 <span
                        class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="text" id="dName" name="dName" data-validate-length-range="1,15"
                             class="form-control col-md-7 col-xs-12" required="required"
                             value="${delivererVO.getDName()!}" placeholder="长度:1-15">
                    </div>
                  </div>
                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="dEmail">邮箱 <span
                        class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="email" id="dEmail" name="dEmail" required="required"
                             class="form-control col-md-7 col-xs-12" value="${delivererVO.getDEmail()!}">
                    </div>
                  </div>
                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="dPhone">手机号 <span
                        class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="tel" id="dPhone" name="dPhone"
                             required="required"
                             pattern="phone" class="form-control col-md-7 col-xs-12"
                             value="${delivererVO.getDPhone()!}" placeholder="输入11位手机号">
                    </div>
                  </div>

                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="dXibieBanji">系别班级 <span
                        class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="text" id="dXibieBanji" name="dXibieBanji" data-validate-length-range="3,15"
                             class="form-control col-md-7 col-xs-12" required="required" value="${delivererVO.getDXibieBanji()!}"
                             placeholder="长度:3-15">
                    </div>
                  </div>


                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="dIdcard">身份证 <span
                        class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="text" id="dIdcard" name="dIdcard" data-validate-length-range="3,20"
                             class="form-control col-md-7 col-xs-12" required="required" value="${delivererVO.getDIdcard()!}"
                             placeholder="请输入有效身份证号">
                    </div>
                  </div>

                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="quyu_option2">区域选择 <span
                        class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="number" class="hidden" name="dQuyu" id="dQuyu" value="${delivererVO.getDQuyu()!'1'}"/>
                      <div class="btn-group" data-toggle="buttons">
                        <label class="btn btn-default <#if delivererVO.getDQuyu() == 1>active</#if>">
                          <input type="radio" name="quyu" id="quyu_option1" value="1" onchange='setValue("dQuyu", 1)'> 东区
                        </label>
                        <label class="btn btn-default <#if delivererVO.getDQuyu() == 2>active</#if>">
                          <input type="radio" name="quyu" id="quyu_option2" value="2" onchange='setValue("dQuyu", 2)'> 西区
                        </label>
                      </div>
                    </div>
                  </div>

                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="dComment">备注
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                    <textarea id="dComment" name="dComment"
                              class="form-control col-md-7 col-xs-12">${delivererVO.getDComment()!}</textarea>
                    </div>
                  </div>
                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="dCreateTime">创建时间
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="text" id="dCreateTime" name="dCreateTime" readonly="readonly"
                             class="form-control col-md-7 col-xs-12" value="${delivererVO.getDCreateTime()!}">
                    </div>
                  </div>
                  <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="dUpdateTime">上一次更新时间
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input type="text" id="dUpdateTime" name="dUpdateTime_" readonly="readonly"
                             class="form-control col-md-7 col-xs-12" value="${delivererVO.getDUpdateTime()!}">
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
                <h2>修改密码<#--<small>修改信息</small>--></h2>
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
                      action="/os/manage/deliver/updatePassword"
                      method="post">
                <#--<span class="section">修改密码</span>-->
                  <div class="item form-group hidden">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="dId2">dId2
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input id="dId2" class="form-control col-md-7 col-xs-12" name="dId" type="text"
                             value="${delivererVO.getDId()!}">
                    </div>
                  </div>

                  <div class="item form-group">
                    <label for="dPassword" class="control-label col-md-3 col-sm-3 col-xs-12">更改密码
                      <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input id="dPassword" type="password" name="dPassword" data-validate-length-range="6,12"
                             class="form-control col-md-7 col-xs-12" required="required" placeholder="长度为6-12">
                    </div>
                  </div>
                  <div class="item form-group">
                    <label for="password2" class="control-label col-md-3 col-sm-3 col-xs-12">确认新密码
                      <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                      <input id="password2" type="password" name="password2" data-validate-length-range="6,12"
                             data-validate-linked="dPassword" class="form-control col-md-7 col-xs-12"
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
                  <h2>修改状态<#--<small>修改信息</small>--></h2>
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
                    状态若修改成“注销”，则快递员账户无法登陆和使用系统 <strong>（相当于注销账户，但仍保留用户信息）</strong>，只有管理员权限可以重新打开。
                  </div>

                  <p></p>
                  <form id="form_state" class="form-horizontal form-label-left" novalidate
                        action="/os/manage/deliver/updateState"
                        method="post">
                  <#--<span class="section">修改状态</span>-->
                    <div class="item form-group hidden">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="dId4">dId4
                      </label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                        <input id="dId4" class="form-control col-md-7 col-xs-12" name="dId" type="text"
                               value="${delivererVO.getDId()!}">
                      </div>
                    </div>
                    <div class="item form-group">
                      <label class="control-label col-md-3 col-sm-3 col-xs-12" for="state_option2">状态 <span
                          class="required">*</span>
                      </label>
                      <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="number" id="dAccountState" name="dAccountState" class="hidden" value="${delivererVO.getDAccountState()!'0'}"/>
                        <div class="btn-group" data-toggle="buttons">
                          <label class="btn btn-default <#if delivererVO.getDAccountState() == 0>active</#if>">
                            <input type="radio" name="state" id="state_option1" value="0" onchange='setValue("dAccountState", 0)'> 上岗
                          </label>
                          <label class="btn btn-default <#if delivererVO.getDAccountState() == 1>active</#if>">
                            <input type="radio" name="state" id="state_option2" value="1" onchange='setValue("dAccountState", 1)'> 注销
                          </label>
                        </div>
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