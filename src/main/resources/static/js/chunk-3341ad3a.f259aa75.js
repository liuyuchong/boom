(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3341ad3a"],{1422:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("d2-container",[a("template",{slot:"header"},[a("div",{staticClass:"module-header"},[a("div",{staticClass:"left-box"},[a("el-form",{attrs:{size:"mini",inline:""}},[a("el-form-item",{attrs:{label:"用户名"}},[a("el-input",{attrs:{placeholder:"检索内容",clearable:""},model:{value:e.query.queryValue,callback:function(t){e.$set(e.query,"queryValue",t)},expression:"query.queryValue"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.getList}},[e._v("查询")])],1)],1)],1),a("div",{staticClass:"right-box"},[a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.handleAdd}},[e._v("新增")])],1)])]),a("d2-module",[a("div",{staticClass:"table-box"},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,stripe:""}},[a("el-table-column",{attrs:{fixed:"",prop:"id",label:"id",width:"150"}}),a("el-table-column",{attrs:{prop:"username",label:"用户名",width:"150"}}),a("el-table-column",{attrs:{prop:"group",label:"分组",width:"150"}}),a("el-table-column",{attrs:{prop:"position",label:"位置",width:"150"}}),a("el-table-column",{attrs:{prop:"phoneNumber",label:"电话号码",width:"150"}}),a("el-table-column",{attrs:{prop:"role",label:"角色",width:"150"},scopedSlots:e._u([{key:"default",fn:function(t){return[1===t.row.role?a("el-tag",{attrs:{type:"success"}},[e._v(" 系统管理员 ")]):e._e(),2===t.row.role?a("el-tag",{attrs:{type:"success"}},[e._v(" 协同者 ")]):e._e(),3===t.row.role?a("el-tag",{attrs:{type:"success"}},[e._v(" 访客 ")]):e._e()]}}])}),a("el-table-column",{attrs:{fixed:"right",label:"操作",width:"200"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(a){return e.handleEdit(t.row)}}},[e._v("编辑")]),a("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(a){return e.handleDelete(t.row)}}},[e._v("删除")])]}}])})],1),a("d2-pagination",{attrs:{marginTop:"",currentPage:e.pager.pageNo,pageSize:e.pager.pageSize,total:e.pager.total},on:{doCurrentChange:e.handleCurrentChange}})],1)]),a("el-dialog",{attrs:{title:e.editTitle,visible:e.dialogFormVisible,width:"700px"},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{attrs:{model:e.form,"label-position":"right"}},[a("el-form-item",{attrs:{label:"用户名","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.username,callback:function(t){e.$set(e.form,"username",t)},expression:"form.username"}})],1),a("el-form-item",{attrs:{label:"group","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.group,callback:function(t){e.$set(e.form,"group",t)},expression:"form.group"}})],1),a("el-form-item",{attrs:{label:"position","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.position,callback:function(t){e.$set(e.form,"position",t)},expression:"form.position"}})],1),a("el-form-item",{attrs:{label:"电话号码","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.phoneNumber,callback:function(t){e.$set(e.form,"phoneNumber",t)},expression:"form.phoneNumber"}})],1),a("el-form-item",{attrs:{label:"角色","label-width":e.formLabelWidth}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.form.role,callback:function(t){e.$set(e.form,"role",t)},expression:"form.role"}},e._l(e.options,(function(e,t){return a("el-option",{key:t,attrs:{label:e.label,value:e.value}})})),1)],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:e.handleSubmit}},[e._v("确 定")])],1)],1)],2)},o=[],s=(a("96cf"),a("1da1")),n=a("9bd2"),i=a("365c"),l={name:"page1",data:function(){return{query:{queryKey:"id",queryValue:""},options:[{label:"系统管理员",value:1},{label:"协同者",value:2},{label:"访客",value:3}],formLabelWidth:"120px",pager:{pageNo:1,pageSize:10,total:10},tableData:[],form:{id:1,username:"",group:"",position:"",phoneNumber:"",role:1},isCreating:!1,dialogFormVisible:!1}},computed:{editTitle:function(){return this.isCreating?"新增":"编辑信息"}},created:function(){this.getList()},methods:{getList:function(){var e=this;return Object(s["a"])(regeneratorRuntime.mark((function t(){var a,r;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return a={},a.pageNo=e.pager.pageNo,a.pageSize=e.pager.pageSize,a.username=e.query.queryValue||"",t.next=6,i["a"].SYS_USER_LIST(a);case 6:r=t.sent,r.users&&r.users.length>0&&(console.log(r.users),e.tableData=r.users,console.log(e.tableData),e.pager.total=r.total);case 8:case"end":return t.stop()}}),t)})))()},handleCurrentChange:function(e){e&&(this.pager.pageNo=e),this.getList()},handleAdd:function(){this.isCreating=!0,this.form={id:1,username:"",group:"",position:"",phoneNumber:"",role:1},this.dialogFormVisible=!0},handleEdit:function(e){this.isCreating=!1;var t=Object.assign({},e);this.form=Object.assign(this.form,t),this.dialogFormVisible=!0},handleDelete:function(e){var t=this,a=e.id,r=[];r=a.constructor!==Array?[a]:Object.assign([],a),this.$confirm("此操作将永久删除id为".concat(e.username,"}的记录, 是否继续?"),"提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){Object(n["a"])({url:"/machine/del",method:"post",data:{ids:r}}).then((function(e){t.$message.success("删除成功"),t.getList()}))})).catch((function(){t.$message({type:"info",message:"已取消删除"})}))},handleSubmit:function(){var e=this;return Object(s["a"])(regeneratorRuntime.mark((function t(){var a,r,o;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:if(e.dialogFormVisible=!1,a=Object.assign({},e.form),delete a.id,!e.isCreating){t.next=9;break}return t.next=6,i["a"].SYS_USER_ADD(a);case 6:r=t.sent,t.next=12;break;case 9:return t.next=11,i["a"].SYS_USER_EDIT(a);case 11:r=t.sent;case 12:o=e.isCreating?"新增":"修改",r?(e.$message.success(o+"成功"),e.getList()):e.$message.error(o+"失败");case 14:case"end":return t.stop()}}),t)})))()}}},c=l,u=(a("dfca"),a("2877")),m=Object(u["a"])(c,r,o,!1,null,"55ff145e",null);t["default"]=m.exports},"9bd2":function(e,t,a){"use strict";a("99af"),a("d3b7");var r=a("4360"),o=a("cebe"),s=a.n(o),n=a("5f72"),i=a("c276"),l=Object({NODE_ENV:"production",VUE_APP_TITLE:"D2Admin",VUE_APP_API:"/api/",VUE_APP_REPO:"https://github.com/d2-projects/d2-admin-start-kit",VUE_APP_I18N_LOCALE:"zh-chs",VUE_APP_I18N_FALLBACK_LOCALE:"en",VUE_APP_ELEMENT_COLOR:"#409EFF",VUE_APP_VERSION:"1.20.1",VUE_APP_BUILD_TIME:"2020-11-1 22:51:09",BASE_URL:"/"}).VUE_APP_CAS_URL;function c(e){var t=new Error(e);throw u(t),t}function u(e){r["a"].dispatch("d2admin/log/add",{type:"error",err:e,info:"数据请求异常"}),"timeout of 5000ms exceeded"===e.message&&(e.message="请求超时"),Object(n["Message"])({message:e.message,type:"error",duration:5e3})}var m=s.a.create({baseURL:"/api/",timeout:1e4});m.interceptors.request.use((function(e){if(!/^https:\/\/|http:\/\//.test(e.url)){var t=i["a"].cookies.get("token");t&&"undefined"!==t&&(e.headers["X-Token"]=t)}return/^\/open\//.test(e.url)&&(e.baseURL="/"),e}),(function(e){console.log(e),Promise.reject(e)})),m.interceptors.response.use((function(e){var t=e.data,a=t.code;if(void 0===a)return t;switch(a){case 0:return t.data;case"xxx":c("".concat(e.config.url,": [ code: xxx ] ").concat(t.msg));break;default:c("".concat(e.config.url,": ").concat(t.message));break}}),(function(e){if(e&&e.response)switch(e.response.status){case 400:e.message="请求错误";break;case 401:window.location.href=e.response.message||"".concat(l,"/login?service=").concat(encodeURIComponent(window.location.href)),e.message="未授权，请登录";break;case 403:e.message="权限不够，如需调整请联系架构组同学";break;case 404:e.message="请求地址出错: ".concat(e.response.config.url);break;case 408:e.message="请求超时";break;case 500:e.message="服务器内部错误";break;case 501:e.message="服务未实现";break;case 502:e.message="网关错误";break;case 503:e.message="服务不可用";break;case 504:e.message="网关超时";break;case 505:e.message="HTTP版本不受支持";break;default:break}return u(e),Promise.reject(e)})),t["a"]=m},dfca:function(e,t,a){"use strict";var r=a("ef44"),o=a.n(r);o.a},ef44:function(e,t,a){}}]);