(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-864f73c2"],{"5b76":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("d2-container",[a("template",{slot:"header"},[a("div",{staticClass:"module-header"},[a("div",{staticClass:"left-box"},[a("el-form",{attrs:{size:"mini",inline:""}},[a("el-form-item",{attrs:{label:"检索日期"}},[a("el-date-picker",{attrs:{type:"date","value-format":"timestamp","picker-options":e.pickerOptions,placeholder:"选择日期时间"},model:{value:e.query.queryValue,callback:function(t){e.$set(e.query,"queryValue",t)},expression:"query.queryValue"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.getList}},[e._v("查询")])],1)],1)],1),a("div",{staticClass:"right-box"})])]),a("d2-module",[a("div",{staticClass:"table-box"},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,stripe:""}},[a("el-table-column",{attrs:{fixed:"",prop:"id",label:"id"}}),a("el-table-column",{attrs:{prop:"displayDate",label:"日期",width:"150"}}),a("el-table-column",{attrs:{prop:"operation",label:"操作"}}),a("el-table-column",{attrs:{prop:"fixCode",label:"fixCode"}}),a("el-table-column",{attrs:{prop:"from",label:"发码起始值",width:"100"}}),a("el-table-column",{attrs:{prop:"to",label:"发码结束值",width:"100"}}),a("el-table-column",{attrs:{prop:"keeper",label:"库存人"}}),a("el-table-column",{attrs:{prop:"consumer",label:"领退人"}}),a("el-table-column",{attrs:{prop:"operator",label:"操作人"}}),a("el-table-column",{attrs:{prop:"store",label:"库存"}}),a("el-table-column",{attrs:{prop:"send",label:"发出"}}),a("el-table-column",{attrs:{prop:"back",label:"退回"}}),a("el-table-column",{attrs:{prop:"consumed",label:"已消耗"}})],1),a("d2-pagination",{attrs:{marginTop:"",currentPage:e.pager.pageNo,pageSize:e.pager.pageSize,total:e.pager.total},on:{doCurrentChange:e.handleCurrentChange}})],1)]),a("el-dialog",{attrs:{title:e.editTitle,visible:e.dialogFormVisible,width:"700px"},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{attrs:{model:e.form,"label-position":"right"}},[a("el-form-item",{attrs:{label:"time","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.time,callback:function(t){e.$set(e.form,"time",t)},expression:"form.time"}})],1),a("el-form-item",{attrs:{label:"name","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),a("el-form-item",{attrs:{label:"fixCode","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.fixCode,callback:function(t){e.$set(e.form,"fixCode",t)},expression:"form.fixCode"}})],1),a("el-form-item",{attrs:{label:"publicCode","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.publicCode,callback:function(t){e.$set(e.form,"publicCode",t)},expression:"form.publicCode"}})],1),a("el-form-item",{attrs:{label:"count","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.count,callback:function(t){e.$set(e.form,"count",t)},expression:"form.count"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:e.handleSubmit}},[e._v("确 定")])],1)],1)],2)},i=[],r=(a("d81d"),a("d3b7"),a("25f0"),a("96cf"),a("1da1")),n=a("9bd2"),l=a("c276"),s=a("365c"),c={name:"page1",data:function(){return{query:{queryKey:"id",queryValue:""},formLabelWidth:"120px",pager:{pageNo:1,pageSize:10,total:10},tableData:[],form:{id:1,time:"",name:"",fixCode:"",publicCode:"",count:""},pickerOptions:{disabledDate:function(e){return e.getTime()>Date.now()},shortcuts:[{text:"今天",onClick:function(e){e.$emit("pick",new Date)}},{text:"昨天",onClick:function(e){var t=new Date;t.setTime(t.getTime()-864e5),e.$emit("pick",t)}},{text:"一周前",onClick:function(e){var t=new Date;t.setTime(t.getTime()-6048e5),e.$emit("pick",t)}}]},isCreating:!1,dialogFormVisible:!1}},computed:{editTitle:function(){return this.isCreating?"新增":"编辑信息"}},created:function(){this.getList()},methods:{getList:function(){var e=this;return Object(r["a"])(regeneratorRuntime.mark((function t(){var a,o;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return a={},e.query.queryValue?a.date=e.query.queryValue:a.date="",a.pageNo=e.pager.pageNo,a.pageSize=e.pager.pageSize,t.next=6,s["a"].DETONATOR_LOG(a);case 6:o=t.sent,o&&(e.tableData=e.fixData(o.records),e.pager.total=o.total);case 8:case"end":return t.stop()}}),t)})))()},fixData:function(e){return e&&0!==e.length?e.map((function(e){return e.displayDate=l["a"].dateFormat(e.date),e})):[]},handleCurrentChange:function(e){e&&(this.pager.pageNo=e),this.getList()},handleAdd:function(){this.isCreating=!0,this.form={id:1,time:"",name:"",fixCode:"",publicCode:"",count:""},this.dialogFormVisible=!0},handleEdit:function(e){this.isCreating=!1;var t=Object.assign({},e);this.form=Object.assign(this.form,t),this.dialogFormVisible=!0},handleDelete:function(e){var t=this,a=e.id,o=[];o=a.constructor!==Array?[a]:Object.assign([],a),this.$confirm("此操作将永久删除id为".concat(a.toString(),"的记录, 是否继续?"),"提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){Object(n["a"])({url:"/machine/del",method:"post",data:{ids:o}}).then((function(e){t.$message.success("删除成功"),t.getList()}))})).catch((function(){t.$message({type:"info",message:"已取消删除"})}))},handleSubmit:function(){this.dialogFormVisible=!1,console.log(this.form)}}},u=c,m=(a("f95b"),a("2877")),d=Object(m["a"])(u,o,i,!1,null,"779b876a",null);t["default"]=d.exports},"6eaf":function(e,t,a){},"9bd2":function(e,t,a){"use strict";a("99af"),a("d3b7");var o=a("4360"),i=a("cebe"),r=a.n(i),n=a("5f72"),l=a("c276"),s=Object({NODE_ENV:"production",VUE_APP_TITLE:"D2Admin",VUE_APP_API:"/api/",VUE_APP_REPO:"https://github.com/d2-projects/d2-admin-start-kit",VUE_APP_I18N_LOCALE:"zh-chs",VUE_APP_I18N_FALLBACK_LOCALE:"en",VUE_APP_ELEMENT_COLOR:"#409EFF",VUE_APP_VERSION:"1.20.1",VUE_APP_BUILD_TIME:"2020-11-1 22:42:46",BASE_URL:"/"}).VUE_APP_CAS_URL;function c(e){var t=new Error(e);throw u(t),t}function u(e){o["a"].dispatch("d2admin/log/add",{type:"error",err:e,info:"数据请求异常"}),"timeout of 5000ms exceeded"===e.message&&(e.message="请求超时"),Object(n["Message"])({message:e.message,type:"error",duration:5e3})}var m=r.a.create({baseURL:"/api/",timeout:1e4});m.interceptors.request.use((function(e){if(!/^https:\/\/|http:\/\//.test(e.url)){var t=l["a"].cookies.get("token");t&&"undefined"!==t&&(e.headers["X-Token"]=t)}return/^\/open\//.test(e.url)&&(e.baseURL="/"),e}),(function(e){console.log(e),Promise.reject(e)})),m.interceptors.response.use((function(e){var t=e.data,a=t.code;if(void 0===a)return t;switch(a){case 0:return t.data;case"xxx":c("".concat(e.config.url,": [ code: xxx ] ").concat(t.msg));break;default:c("".concat(e.config.url,": ").concat(t.message));break}}),(function(e){if(e&&e.response)switch(e.response.status){case 400:e.message="请求错误";break;case 401:window.location.href=e.response.message||"".concat(s,"/login?service=").concat(encodeURIComponent(window.location.href)),e.message="未授权，请登录";break;case 403:e.message="权限不够，如需调整请联系架构组同学";break;case 404:e.message="请求地址出错: ".concat(e.response.config.url);break;case 408:e.message="请求超时";break;case 500:e.message="服务器内部错误";break;case 501:e.message="服务未实现";break;case 502:e.message="网关错误";break;case 503:e.message="服务不可用";break;case 504:e.message="网关超时";break;case 505:e.message="HTTP版本不受支持";break;default:break}return u(e),Promise.reject(e)})),t["a"]=m},f95b:function(e,t,a){"use strict";var o=a("6eaf"),i=a.n(o);i.a}}]);