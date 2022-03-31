<template>
	<el-card>
		<el-col>
			<el-input style="width: 400px;"
				placeholder="请输入药材名"
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
			<el-button style="margin-left: -500px;position: absolute;" type="primary" @click="dialogFormVisible = !dialogFormVisible">添加药材</el-button>
			<el-button style="margin-left: -400px;position: absolute;" type="primary" @click="dialogFormVisible1 = !dialogFormVisible1">删除药材</el-button>
		</el-col>
		<el-dialog title="添加药材" :model-value="dialogFormVisible">
			<el-form :model="form">
			<el-form-item label="中文名" :label-width="formLabelWidth">
		      <el-input v-model="form.question" autocomplete="off"></el-input>
		    </el-form-item>
			<el-form-item label="别名" :label-width="formLabelWidth">
			  <el-input v-model="form.question" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="药材性状" :label-width="formLabelWidth">
			  <el-input v-model="form.question" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="功效与作用" :label-width="formLabelWidth">
			  <el-input v-model="form.question" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="产地分布" :label-width="formLabelWidth">
			  <el-input v-model="form.question" autocomplete="off"></el-input>
			</el-form-item>
			<div slot="footer" class="dialog-footer">
					<el-button @click="dialogFormVisible = false">取 消</el-button>
					<el-button type="primary" @click="addQuestion()">确 定</el-button>
			</div>
			</el-form>
		</el-dialog>
		<el-dialog title="删除此药材" :model-value="dialogFormVisible1">
			<el-form :model="form1">
			<div slot="footer" class="dialog-footer">
					<el-button @click="dialogFormVisible = false">取 消</el-button>
					<el-button type="primary" @click="deleteQuestion()">确 定</el-button>
			</div>
			</el-form>
		</el-dialog>
		<!-- 渲染表格请求 -->
		<el-table
		    :data="drugList"
		    border
		    style="width: 100%">
		    <el-table-column
				type="index"
				width="50">
		    </el-table-column>
		    <el-table-column
				prop="chineseName"
				label="中文名"
				width="60">
		    </el-table-column>
			<el-table-column
				prop="alias"
				label="别名"
				width="160">
		    </el-table-column>
			<el-table-column
				prop="medicinalProperties"
				label="药材性状"
				width="300">
			</el-table-column>
			<el-table-column
				prop="efficacyAndFunction"
				label="功效与作用">
			</el-table-column>
			<el-table-column
				prop="originDistribution"
				label="产地分布">
			</el-table-column>
			<!-- <el-table-column
				label="操作"
				width="120">
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
			page-size="2"
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
				drugList:[],
				pagenum:1,
				total:0,
				all:true,
				search:false,
				dialogFormVisible:false,
				form:{
					question:'',
				},
				dialogFormVisible1:false
			}
		},
		methods: {
			getAllChineseMedicine(val){
				var that = this
				that.all = true
				that.search = false
				this.$http.post('http://dvwbxngn.dnat.tech/getAllChineseMedicine',{
					pagenum:val
				})
				.then(function(res){
					that.drugList = res.data.chineseMedicineList,
					that.total = res.data.total
				})
				.catch(function(err){
					console.log(err)
				})
			},
			startsearch(val){
				var that = this
				this.$http.post('http://dvwbxngn.dnat.tech/searchChineseMedicine',{
					pagenum:val,
					search:that.input
				})
				.then(function(res){
					that.drugList = res.data.chineseMedicineList,
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
			//当前页码发生改变触发
			handleCurrentChange(val){
				var that = this
				if(that.all){
					this.getAllChineseMedicine(val)
				}
				else {
					this.startsearch(val)
				}
			}
		},
		created(){
			this.getAllChineseMedicine(1)
		}
	}
</script>

<style>
</style>
