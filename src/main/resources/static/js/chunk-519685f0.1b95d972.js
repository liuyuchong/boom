(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-519685f0"],{"0a6e":function(e,t,a){},"0c24":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("d2-container",[a("template",{slot:"header"},[a("div",{staticClass:"module-header"},[a("div",{staticClass:"left-box"},[a("el-form",{attrs:{size:"mini",inline:""}},[a("el-form-item",{attrs:{label:"检索日期"}},[a("el-row",{attrs:{gutter:20,type:"flex",justify:"start"}},[a("el-col",{attrs:{span:11}},[a("el-date-picker",{attrs:{type:"date","value-format":"timestamp","picker-options":e.pickerOptions,placeholder:"选择日期时间"},model:{value:e.queryForm.startTime,callback:function(t){e.$set(e.queryForm,"startTime",t)},expression:"queryForm.startTime"}})],1),a("el-col",{staticClass:"line",attrs:{span:4}},[e._v("-")]),a("el-col",{attrs:{span:16}},[a("el-date-picker",{attrs:{type:"date","value-format":"timestamp","picker-options":e.pickerOptions,placeholder:"选择日期时间"},model:{value:e.queryForm.endTime,callback:function(t){e.$set(e.queryForm,"endTime",t)},expression:"queryForm.endTime"}})],1)],1)],1),a("el-form-item",{attrs:{label:"保管人"}},[a("el-input",{attrs:{placeholder:""},model:{value:e.queryForm.keeper,callback:function(t){e.$set(e.queryForm,"keeper",t)},expression:"queryForm.keeper"}})],1),a("el-form-item",{attrs:{label:"领退人"}},[a("el-input",{attrs:{placeholder:""},model:{value:e.queryForm.consumer,callback:function(t){e.$set(e.queryForm,"consumer",t)},expression:"queryForm.consumer"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.getList}},[e._v("查询")])],1)],1)],1)])]),a("d2-module",[a("p",[e._v(" 当天库存:"+e._s(e.stock))]),a("div",{staticClass:"table-box"},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,stripe:""}},[a("el-table-column",{attrs:{fixed:"",prop:"id",label:"id"}}),a("el-table-column",{attrs:{prop:"displayDate",label:"日期",width:"150"}}),a("el-table-column",{attrs:{prop:"operation",label:"操作"}}),a("el-table-column",{attrs:{prop:"box",label:"箱号",width:"100"}}),a("el-table-column",{attrs:{prop:"col",label:"柱码",width:"100"}}),a("el-table-column",{attrs:{prop:"keeper",label:"保管人"}}),a("el-table-column",{attrs:{prop:"consumer",label:"领退人"}}),a("el-table-column",{attrs:{prop:"operator",label:"操作人"}}),a("el-table-column",{attrs:{prop:"store",label:"入库"}}),a("el-table-column",{attrs:{prop:"send",label:"发出"}}),a("el-table-column",{attrs:{prop:"back",label:"退回"}})],1),a("el-pagination",{attrs:{"current-page":e.pager.pageNo,"page-size":10,layout:"total, prev, pager, next",total:e.pager.total},on:{"update:currentPage":function(t){return e.$set(e.pager,"pageNo",t)},"update:current-page":function(t){return e.$set(e.pager,"pageNo",t)},"current-change":e.handleCurrentChange}})],1)]),a("el-dialog",{attrs:{title:e.editTitle,visible:e.dialogFormVisible,width:"700px"},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{attrs:{model:e.form,"label-position":"right"}},[a("el-form-item",{attrs:{label:"time","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.time,callback:function(t){e.$set(e.form,"time",t)},expression:"form.time"}})],1),a("el-form-item",{attrs:{label:"name","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),a("el-form-item",{attrs:{label:"固定码","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.fixCode,callback:function(t){e.$set(e.form,"fixCode",t)},expression:"form.fixCode"}})],1),a("el-form-item",{attrs:{label:"publicCode","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.publicCode,callback:function(t){e.$set(e.form,"publicCode",t)},expression:"form.publicCode"}})],1),a("el-form-item",{attrs:{label:" 重量","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.count,callback:function(t){e.$set(e.form,"count",t)},expression:"form.count"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:e.handleSubmit}},[e._v("确 定")])],1)],1)],2)},r=[],i=(a("d81d"),a("d3b7"),a("25f0"),a("96cf"),a("1da1")),s=a("9bd2"),n=a("c276"),l=a("365c"),c={name:"page1",data:function(){return{queryForm:{startTime:"",endTime:"",keeper:"",consumer:""},stock:0,formLabelWidth:"120px",pager:{pageNo:1,pageSize:10,total:10},tableData:[],form:{id:1,time:"",name:"",fixCode:"",publicCode:"",count:""},pickerOptions:{disabledDate:function(e){return e.getTime()>Date.now()},shortcuts:[{text:"今天",onClick:function(e){e.$emit("pick",new Date)}},{text:"昨天",onClick:function(e){var t=new Date;t.setTime(t.getTime()-864e5),e.$emit("pick",t)}},{text:"一周前",onClick:function(e){var t=new Date;t.setTime(t.getTime()-6048e5),e.$emit("pick",t)}}]},isCreating:!1,dialogFormVisible:!1}},computed:{editTitle:function(){return this.isCreating?"新增":"编辑信息"}},created:function(){this.getList()},methods:{getList:function(){var e=this;return Object(i["a"])(regeneratorRuntime.mark((function t(){var a,o;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return a=Object.assign({},e.queryForm),a.pageNo=e.pager.pageNo,a.pageSize=e.pager.pageSize,a=n["a"].fixEmptyVal(a),t.next=6,l["a"].EXPLOSIVE_LOG(a);case 6:o=t.sent,o&&(e.tableData=e.fixData(o.records),e.stock=o.stock,e.pager.total=o.total);case 8:case"end":return t.stop()}}),t)})))()},fixData:function(e){return e&&0!==e.length?e.map((function(e){return e.displayDate=n["a"].dateFormat(e.date),e})):[]},handleCurrentChange:function(e){e&&(this.pager.pageNo=e),this.getList()},handleAdd:function(){this.isCreating=!0,this.form={id:1,time:"",name:"",fixCode:"",publicCode:"",count:""},this.dialogFormVisible=!0},handleEdit:function(e){this.isCreating=!1;var t=Object.assign({},e);this.form=Object.assign(this.form,t),this.dialogFormVisible=!0},handleDelete:function(e){var t=this,a=e.id,o=[];o=a.constructor!==Array?[a]:Object.assign([],a),this.$confirm("此操作将永久删除id为".concat(a.toString(),"的记录, 是否继续?"),"提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){Object(s["a"])({url:"/machine/del",method:"post",data:{ids:o}}).then((function(e){t.$message.success("删除成功"),t.getList()}))})).catch((function(){t.$message({type:"info",message:"已取消删除"})}))},handleSubmit:function(){this.dialogFormVisible=!1,console.log(this.form)}}},m=c,u=(a("8cef"),a("2877")),p=Object(u["a"])(m,o,r,!1,null,"43893980",null);t["default"]=p.exports},"8cef":function(e,t,a){"use strict";var o=a("0a6e"),r=a.n(o);r.a},"9bd2":function(e,t,a){"use strict";a("99af"),a("d3b7");var o=a("4360"),r=a("cebe"),i=a.n(r),s=a("5f72"),n=a("c276"),l=Object({NODE_ENV:"production",VUE_APP_TITLE:"民爆信息管理系统",VUE_APP_API:"/api/",VUE_APP_REPO:"https://github.com/d2-projects/d2-admin-start-kit",VUE_APP_I18N_LOCALE:"zh-chs",VUE_APP_I18N_FALLBACK_LOCALE:"en",VUE_APP_ELEMENT_COLOR:"#409EFF",VUE_APP_VERSION:"1.20.1",VUE_APP_BUILD_TIME:"2020-11-5 22:59:38",BASE_URL:"/"}).VUE_APP_CAS_URL;function c(e){var t=new Error(e);throw m(t),t}function m(e){o["a"].dispatch("d2admin/log/add",{type:"error",err:e,info:"数据请求异常"}),"timeout of 5000ms exceeded"===e.message&&(e.message="请求超时"),Object(s["Message"])({message:e.message,type:"error",duration:5e3})}var u=i.a.create({baseURL:"/api/",timeout:1e4});u.interceptors.request.use((function(e){if(!/^https:\/\/|http:\/\//.test(e.url)){var t=n["a"].cookies.get("token");t&&"undefined"!==t&&(e.headers["X-Token"]=t)}return/^\/open\//.test(e.url)&&(e.baseURL="/"),e}),(function(e){console.log(e),Promise.reject(e)})),u.interceptors.response.use((function(e){var t=e.data,a=t.code;if(void 0===a)return t;switch(a){case 0:return t.data;case"xxx":c("".concat(e.config.url,": [ code: xxx ] ").concat(t.msg));break;default:c("".concat(e.config.url,": ").concat(t.message));break}}),(function(e){if(e&&e.response)switch(e.response.status){case 400:e.message="请求错误";break;case 401:window.location.href=e.response.message||"".concat(l,"/login?service=").concat(encodeURIComponent(window.location.href)),e.message="未授权，请登录";break;case 403:e.message="权限不够，如需调整请联系架构组同学";break;case 404:e.message="请求地址出错: ".concat(e.response.config.url);break;case 408:e.message="请求超时";break;case 500:e.message="服务器内部错误";break;case 501:e.message="服务未实现";break;case 502:e.message="网关错误";break;case 503:e.message="服务不可用";break;case 504:e.message="网关超时";break;case 505:e.message="HTTP版本不受支持";break;default:break}return m(e),Promise.reject(e)})),t["a"]=u}}]);