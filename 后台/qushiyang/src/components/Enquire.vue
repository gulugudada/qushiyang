<template>
	<el-card>
		<el-col>
			<el-input style="width: 400px;"
				placeholder="请输入问题"
				v-model="input"
				clearable>
			</el-input>
			<el-button slot="append" icon="el-icon-search" @click="getSearch()"></el-button>
		</el-col>
		<el-col >
			<el-input style="width: 400px;margin-top: 0px;margin-right: 500px;"
				placeholder="请输入序号"
				v-model="input1"
				clearable>
			</el-input>
			<el-button style="margin-left: -500px;position: absolute;" type="primary" @click="dialogFormVisible = !dialogFormVisible">增加问题</el-button>
			<el-button style="margin-left: -400px;position: absolute;" type="primary" @click="dialogFormVisible1 = !dialogFormVisible1">删除问题</el-button>
		</el-col>
		<el-dialog title="添加问题" :model-value="dialogFormVisible">
			<el-form :model="form">
			<el-form-item label="问题" :label-width="formLabelWidth">
		      <el-input v-model="form.question" autocomplete="off"></el-input>
		    </el-form-item>
			<el-form-item label="回答" :label-width="formLabelWidth">
			  <el-input v-model="form.anson" autocomplete="off"></el-input>
			</el-form-item>
			<div slot="footer" class="dialog-footer">
					<el-button @click="dialogFormVisible = false">取 消</el-button>
					<el-button type="primary" @click="addQuestion()">确 定</el-button>
			</div>
			</el-form>
		</el-dialog>
		<el-dialog title="删除此问题" :model-value="dialogFormVisible1">
			<el-form :model="form1">
			<div slot="footer" class="dialog-footer">
					<el-button @click="dialogFormVisible = false">取 消</el-button>
					<el-button type="primary" @click="deleteQuestion()">确 定</el-button>
			</div>
			</el-form>
		</el-dialog>
		<!-- 渲染表格请求 -->
		<el-table
		    :data="enquireList"
		    border
		    style="width: 100%">
		    <el-table-column
				type="index"
				width="50">
		    </el-table-column>
		    <el-table-column
				prop="question"
				label="问题"
				width="120">
		    </el-table-column>
			<el-table-column
				prop="answer"
				label="回答"
				width="800">
		    </el-table-column>
			<!-- <el-table-column
				label="操作">
				<el-tooltip class="item" effect="dark" content="修改用户" placement="top">
				    <el-button type="primary" icon="el-icon-edit" circle></el-button>
				</el-tooltip>
				<el-tooltip class="item" effect="dark" content="删除用户" placement="top">
				    <el-button type="danger" icon="el-icon-delete" circle></el-button>
				</el-tooltip>
			</el-table-column> -->
		</el-table>
		<!-- 分页功能 -->
		<el-pagination
		  	background
		  	layout="prev, pager, next"
			page-size="5"
			:current-page="pagenum"
			@current-change="handleCurrentChange"
		  	:page-count="total">
		</el-pagination>
	</el-card>
</template>

<script>
	export default {
		data(){
			return {
				input:'',
				enquireList:[],
				pagenum:1,
				total:0,
				all:true,
				search:false,
				dialogFormVisible:false,
				dialogFormVisible1:false,
				form:{
					question:'',
					anson:'',
					
				}
			}
		},
		methods: {
			getEnquireList(val){
				var that = this
				that.all = true
				that.search = false
				this.$http.post('http://dvwbxngn.dnat.tech/getAllEnquire',{
					pagenum:val
				})
				.then(function(res){
					that.enquireList = res.data.enquireList,
					that.total = res.data.total
				})
				.catch(function(err){
					console.log(err)
				})
			},
			startsearch(val){
				var that = this
				this.$http.post('http://dvwbxngn.dnat.tech/searchEnquire',{
					pagenum:val,
					search:that.input
				})
				.then(function(res){
					that.enquireList = res.data.enquireList,
					that.total = res.data.total
				})
				.catch(function(err){
					console.log(err)
				})
			},
			getSearch(){
				var that = this
				this.startsearch(1)
				that.pagenum = 1
				that.search = true
				that.all = false
			},
			addQuestion(){
				
			},
			deleteQuestion(){
				
			},
			//当前页码发生改变触发
			handleCurrentChange(val){
				var that = this
				if(that.all){
					this.getEnquireList(val)
				}
				else {
					this.startsearch(val)
				}
			}
		},
		created(){
			this.getEnquireList(1)
		}
	}
</script>

<style>
</style>
