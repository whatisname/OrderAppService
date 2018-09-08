<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <!-- Meta, title, CSS, favicons, etc. -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>订单列表 | </title>

  <!-- Bootstrap -->
  <link href="../../static/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Font Awesome -->
  <link href="../../static/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <!-- NProgress -->
  <link href="../../static/vendors/nprogress/nprogress.css" rel="stylesheet">
  <!-- iCheck -->
  <link href="../../static/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
  <!-- Datatables -->
  <link href="../../static/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
  <link href="../../static/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
  <link href="../../static/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
  <link href="../../static/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
  <link href="../../static/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">

  <!-- Custom Theme Style -->
  <link href="../../static/build/css/custom.min.css" rel="stylesheet">

  <!-- PNotify -->
  <link href="../../static/vendors/pnotify/dist/pnotify.css" rel="stylesheet">
  <link href="../../static/vendors/pnotify/dist/pnotify.buttons.css" rel="stylesheet">
  <link href="../../static/vendors/pnotify/dist/pnotify.nonblock.css" rel="stylesheet">
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
            <h3>订单列表
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

        <div class="col-md-12 col-sm-12 col-xs-12">
          <div class="x_panel">
            <div class="x_title">
              <h2>订单列表 <#--<small></small>--></h2>
              <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                </li>
                <!--<li class="dropdown">-->
                <!--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i-->
                <!--class="fa fa-wrench"></i></a>-->
                <!--<ul class="dropdown-menu" role="menu">-->
                <!--<li><a href="#">设置 1</a>-->
                <!--</li>-->
                <!--<li><a href="#">设置 2</a>-->
                <!--</li>-->
                <!--</ul>-->
                <!--</li>-->
                <!--<li><a class="close-link"><i class="fa fa-close"></i></a></li>-->
              </ul>
              <div class="clearfix"></div>
            </div>
            <div class="x_content">
              <p class="text-muted font-13 m-b-30">
                <!--商家列表-->
              </p>
              <!--<div class="table-responsive">-->
              <table id="datatable-checkbox" class="table table-bordered table-condensed jambo_table bulk_action"
                     cellspacing="0" width="100%">
                <thead>
                <tr class="headings">
                  <th>
                    <input type="checkbox" id="check-all" class="flat">
                  </th>
                  <th class="column-title">订单号</th>
                  <th class="column-title">快递费</th>
                  <th class="column-title">总价</th>
                  <th class="column-title">支付日期</th>
                  <th class="column-title">送达日期</th>
                  <th class="column-title">收货日期</th>
                  <th class="column-title">订单状态</th>
                  <th class="column-title">地址</th>
                  <th class="column-title">备注</th>

                  <th class="column-title">sid(学生id)</th>
                  <th class="column-title">bid(商店id)</th>
                  <th class="column-title">did(快递员id)</th>
                  <th class="column-title no-link last"><span class="nobr">操作</span>
                  </th>
                  <th class="bulk-actions" colspan="13">
                    <a class="antoo" style="color:#fff; font-weight:500;">( <span class="action-cnt"> </span> ) 操作： </a>

                    <!--<a class="" href="/delete/">删除所选</a>-->
                  </th>
                </tr>
                </thead>

                <tbody>
                  <#list (orderVOPage.getContent())! as item>
                  <tr class="even pointer">
                    <td class="a-center ">
                      <input type="checkbox" class="flat" name="table_records" data-opid="${item.getOPId()!}">
                    </td>
                    <td class=" ">${item.getOPId()!} </td>
                    <td class=" ">${item.getOPDeliverFee()!} </td>
                    <td class=" ">${item.getOPSum()!}</td>
                    <td class=" ">${item.getOPPaymentDate()!}</td>
                    <td class=" ">${item.getOPDeliverArriveDate()!}</td>
                    <td class=" ">${item.getOPFinishDate()!} </td>
                    <td class=" ">${item.getState()!} </td>
                    <td class=" ">${item.getOPAddress()!} </td>
                    <td>${item.getOPComment()!} </td>
                    <td class="a-right a-right ">${item.getSId()!}</td>
                    <td class="a-right a-right ">${item.getBId()!}</td>
                    <td class="a-right a-right ">${item.getDId()!}</td>
                    <td class=" last">
                      <#--<a class="btn btn-default btn-xs" href="/os/manage/order/detail?OPId=${item.getOPId()!}">查看详情</a>-->
                      <#--<a class="btn btn-danger btn-xs" href="javascript:"-->
                         <#--onclick="delete_item('${item.getOPId()!}', '')">删除</a>-->
                    </td>
                  </tr>
                  </#list>
                </tbody>
              </table>
              <!--</div>-->

            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- /page content -->

  <script>
    // function delete_item(bid, name) {
    //   // $('input:checked').get
    //   // 发送后台
    //   $.get("/os/manage/order/delete", {
    //     bid: bid
    //   }).done(function (data) {
    //     if (data.code == 1) {
    //       // 结果显示
    //       var notice = new PNotify({
    //         title: '删除成功',
    //         text: '店铺（' + name + '） id:' + bid + '已删除',
    //         type: 'success',
    //         styling: 'bootstrap3',
    //         buttons: {
    //           closer: false,
    //           sticker: false
    //         }
    //       });
    //       notice.get().click(function () {
    //         notice.remove();
    //       });
    //       //刷新页面
    //       setTimeout(function () {
    //         location.reload();
    //       }, 1000);
    //     } else {
    //       // 结果显示
    //       var notice = new PNotify({
    //         title: '删除失败',
    //         text: '原因: ' + data.message,
    //         type: 'warning',
    //         styling: 'bootstrap3',
    //         buttons: {
    //           closer: false,
    //           sticker: false
    //         }
    //       });
    //       notice.get().click(function () {
    //         notice.remove();
    //       });
    //     }
    //
    //   }).fail(function (data) {
    //     alert("error");
    //   });
    //
    // }
  </script>

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
<!-- iCheck -->
<script src="../../static/vendors/iCheck/icheck.min.js"></script>
<!-- Datatables -->
<script src="../../static/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="../../static/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script src="../../static/vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
<script src="../../static/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
<script src="../../static/vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
<script src="../../static/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
<script src="../../static/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
<script src="../../static/vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
<script src="../../static/vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
<script src="../../static/vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
<script src="../../static/vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
<script src="../../static/vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
<script src="../../static/vendors/jszip/dist/jszip.min.js"></script>
<script src="../../static/vendors/pdfmake/build/pdfmake.min.js"></script>
<script src="../../static/vendors/pdfmake/build/vfs_fonts.js"></script>

<!-- Custom Theme Scripts -->
<script src="../../static/build/js/custom.min.js"></script>
<!-- PNotify -->
<script src="../../static/vendors/pnotify/dist/pnotify.js"></script>
<script src="../../static/vendors/pnotify/dist/pnotify.buttons.js"></script>
<script src="../../static/vendors/pnotify/dist/pnotify.nonblock.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
    $('#example').dataTable( {
      "language": {
        "url": "dataTables.german.lang"
      }
    } );
  } );
</script>

</body>
</html>