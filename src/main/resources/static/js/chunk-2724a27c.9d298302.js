(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2724a27c"],{"2fcf":function(e,t,a){"use strict";a.r(t);var i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("d2-container",[a("template",{slot:"header"},[a("div",{staticClass:"module-header"},[a("div",{staticClass:"left-box"},[a("el-form",{attrs:{size:"mini",inline:""}},[a("el-form-item",[a("el-select",{attrs:{placeholder:"检索字段",clearable:""},model:{value:e.query.queryKey,callback:function(t){e.$set(e.query,"queryKey",t)},expression:"query.queryKey"}},[a("el-option",{attrs:{label:"id",value:"id"}}),a("el-option",{attrs:{label:"name",value:"name"}}),a("el-option",{attrs:{label:"time",value:"time"}})],1)],1),a("el-form-item",[a("el-input",{attrs:{placeholder:"检索内容"},model:{value:e.query.queryValue,callback:function(t){e.$set(e.query,"queryValue",t)},expression:"query.queryValue"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.getList}},[e._v("查询")])],1)],1)],1),a("div",{staticClass:"right-box"},[a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.handleAdd}},[e._v("新增")])],1)])]),a("d2-module",[a("div",{staticClass:"table-box"},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,stripe:""}},[a("el-table-column",{attrs:{fixed:"",prop:"id",label:"id",width:"150"}}),a("el-table-column",{attrs:{fixed:"",prop:"time",label:"time",width:"250"}}),a("el-table-column",{attrs:{fixed:"",prop:"name",label:"name",width:"150"}}),a("el-table-column",{attrs:{fixed:"",prop:"fixCode",label:"固定码",width:"150"}}),a("el-table-column",{attrs:{fixed:"",prop:"publicCode",label:"publicCode",width:"150"}}),a("el-table-column",{attrs:{fixed:"",prop:"count",label:" 重量",width:"150"}}),a("el-table-column",{attrs:{fixed:"right",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(a){return e.handleEdit(t.row)}}},[e._v("编辑")]),a("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(a){return e.handleDelete(t.row)}}},[e._v("删除")])]}}])})],1),a("d2-pagination",{attrs:{marginTop:"",currentPage:e.pager.pageNo,pageSize:e.pager.pageSize,total:e.pager.total},on:{doCurrentChange:e.handleCurrentChange}})],1)]),a("el-dialog",{attrs:{title:e.editTitle,visible:e.dialogFormVisible,width:"700px"},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{attrs:{model:e.form,"label-position":"right"}},[a("el-form-item",{attrs:{label:"time","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.time,callback:function(t){e.$set(e.form,"time",t)},expression:"form.time"}})],1),a("el-form-item",{attrs:{label:"name","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),a("el-form-item",{attrs:{label:"固定码","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.fixCode,callback:function(t){e.$set(e.form,"fixCode",t)},expression:"form.fixCode"}})],1),a("el-form-item",{attrs:{label:"publicCode","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.publicCode,callback:function(t){e.$set(e.form,"publicCode",t)},expression:"form.publicCode"}})],1),a("el-form-item",{attrs:{label:" 重量","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.count,callback:function(t){e.$set(e.form,"count",t)},expression:"form.count"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:e.handleSubmit}},[e._v("确 定")])],1)],1)],2)},o=[],r=(a("d3b7"),a("25f0"),a("9bd2")),l={name:"page1",data:function(){return{query:{queryKey:"id",queryValue:""},formLabelWidth:"120px",pager:{pageNo:2,pageSize:10,total:200},tableData:[{id:1,time:"2020-10-25 13:36:29",name:"XXX雷管名称",fixCode:"XXX雷管固定码",publicCode:"1-9999",count:"200"},{id:2,time:"2020-10-25 13:36:29",name:"XXX雷管名称",fixCode:"XXX雷管固定码",publicCode:"1-9999",count:"200"},{id:2,time:"2020-10-25 13:36:29",name:"XXX雷管名称",fixCode:"XXX雷管固定码",publicCode:"1-9999",count:"200"}],form:{id:1,time:"",name:"",fixCode:"",publicCode:"",count:""},isCreating:!1,dialogFormVisible:!1}},computed:{editTitle:function(){return this.isCreating?"新增":"编辑信息"}},methods:{getList:function(){var e=this,t={};t[this.query.queryKey]=this.query.queryValue,Object(r["a"])({url:"/api/list",method:"get"}).then((function(t){t&&t.length>0&&(e.tableData=t)})).catch((function(t){console.log(t),e.$message.error("获取列表信息失败")}))},handleCurrentChange:function(e){e&&(this.pager.pageNo=e),this.getList()},handleAdd:function(){this.isCreating=!0,this.form={id:1,time:"",name:"",fixCode:"",publicCode:"",count:""},this.dialogFormVisible=!0},handleEdit:function(e){this.isCreating=!1;var t=Object.assign({},e);this.form=Object.assign(this.form,t),this.dialogFormVisible=!0},handleDelete:function(e){var t=this,a=e.id,i=[];i=a.constructor!==Array?[a]:Object.assign([],a),this.$confirm("此操作将永久删除id为".concat(a.toString(),"的记录, 是否继续?"),"提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){Object(r["a"])({url:"/machine/del",method:"post",data:{ids:i}}).then((function(e){t.$message.success("删除成功"),t.getList()}))})).catch((function(){t.$message({type:"info",message:"已取消删除"})}))},handleSubmit:function(){this.dialogFormVisible=!1,console.log(this.form)}}},n=l,s=(a("4539"),a("2877")),c=Object(s["a"])(n,i,o,!1,null,"d9e1b94a",null);t["default"]=c.exports},4539:function(e,t,a){"use strict";var i=a("79f7"),o=a.n(i);o.a},"79f7":function(e,t,a){},"9bd2":function(e,t,a){"use strict";a("99af"),a("d3b7");var i=a("4360"),o=a("cebe"),r=a.n(o),l=a("5f72"),n=a("c276"),s=Object({NODE_ENV:"production",VUE_APP_TITLE:"民爆信息管理系统",VUE_APP_API:"/api/",VUE_APP_REPO:"https://github.com/d2-projects/d2-admin-start-kit",VUE_APP_I18N_LOCALE:"zh-chs",VUE_APP_I18N_FALLBACK_LOCALE:"en",VUE_APP_ELEMENT_COLOR:"#409EFF",VUE_APP_VERSION:"zhangjunyi",VUE_APP_BUILD_TIME:"2020-11-9 17:01:20",BASE_URL:"/"}).VUE_APP_CAS_URL;function c(e){var t=new Error(e);throw u(t),t}function u(e){i["a"].dispatch("d2admin/log/add",{type:"error",err:e,info:"数据请求异常"}),"timeout of 5000ms exceeded"===e.message&&(e.message="请求超时"),Object(l["Message"])({message:e.message,type:"error",duration:5e3})}var d=r.a.create({baseURL:"/api/",timeout:1e4});d.interceptors.request.use((function(e){if(!/^https:\/\/|http:\/\//.test(e.url)){var t=n["a"].cookies.get("token");t&&"undefined"!==t&&(e.headers["X-Token"]=t)}return/^\/open\//.test(e.url)&&(e.baseURL="/"),e}),(function(e){console.log(e),Promise.reject(e)})),d.interceptors.response.use((function(e){var t=e.data,a=t.code;if(void 0===a)return t;switch(a){case 0:return t.data;case"xxx":c("".concat(e.config.url,": [ code: xxx ] ").concat(t.msg));break;default:c("".concat(e.config.url,": ").concat(t.message));break}}),(function(e){if(e&&e.response)switch(e.response.status){case 400:e.message="请求错误";break;case 401:window.location.href=e.response.message||"".concat(s,"/login?service=").concat(encodeURIComponent(window.location.href)),e.message="未授权，请登录";break;case 403:e.message="权限不够，如需调整请联系架构组同学";break;case 404:e.message="请求地址出错: ".concat(e.response.config.url);break;case 408:e.message="请求超时";break;case 500:e.message="服务器内部错误";break;case 501:e.message="服务未实现";break;case 502:e.message="网关错误";break;case 503:e.message="服务不可用";break;case 504:e.message="网关超时";break;case 505:e.message="HTTP版本不受支持";break;default:break}return u(e),Promise.reject(e)})),t["a"]=d}}]);