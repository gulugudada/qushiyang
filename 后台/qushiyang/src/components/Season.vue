<template>
	<el-card>
		<el-col>
			<el-input style="width: 400px;"
				placeholder="请输入季节或食谱名"
				v-model="input"
				clearable>
			</el-input>
			<el-button slot="append" icon="el-icon-search" @click="getSearch()"></el-button>
		</el-col>
		<el-col>
			<el-button style="margin-left: 50px;position: absolute;" type="primary">添加食谱</el-button>
		</el-col>
		<el-col >
			<el-input style="width: 400px;margin-top: 0px;margin-right: 500px;"
				placeholder="请输入序号"
				v-model="input1"
				clearable>
			</el-input>
			<el-button style="margin-left: -500px;position: absolute;" type="primary" @click="dialogFormVisible = !dialogFormVisible">添加食谱</el-button>
			<el-button style="margin-left: -395px;position: absolute;" type="primary" @click="dialogFormVisible1 = !dialogFormVisible1">删除食谱</el-button>
		</el-col>
		<el-dialog title="添加食谱" :model-value="dialogFormVisible">
			<el-form :model="form">
			<el-form-item label="季节" :label-width="formLabelWidth">
		      <el-input v-model="form.season" autocomplete="off"></el-input>
		    </el-form-item>
			<el-form-item label="食谱名" :label-width="formLabelWidth">
			  <el-input v-model="form.cookbook" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="做法" :label-width="formLabelWidth">
			  <el-input v-model="form.method" autocomplete="off"></el-input>
			</el-form-item>
			<div slot="footer" class="dialog-footer">
					<el-button @click="dialogFormVisible = false">取 消</el-button>
					<el-button type="primary" @click="adddish()">确 定</el-button>
			</div>
			</el-form>
		</el-dialog>
		<el-dialog title="删除此食谱" :model-value="dialogFormVisible1">
			<el-form :model="form1">
			<div slot="footer" class="dialog-footer">
					<el-button @click="dialogFormVisible1 = false">取 消</el-button>
					<el-button type="primary" @click="deletedish()">确 定</el-button>
			</div>
			</el-form>
		</el-dialog>
		<!-- 渲染表格请求 -->
		<el-table
		    :data="seasontuijianList"
		    border
		    style="width: 100%">
		    <el-table-column
				type="index"
				width="50">
		    </el-table-column>
		    <el-table-column
				prop="season"
				label="季节"
				width="60">
		    </el-table-column>
			<el-table-column
				prop="cookbook"
				label="食谱名"
				width="160">
		    </el-table-column>
			<el-table-column
				prop="method"
				label="做法">
			</el-table-column>
			<!-- <el-table-column
				label="操作"
				width="120">
				<el-tooltip class="item" effect="dark" content="修改食谱" placement="top">
				    <el-button type="primary" icon="el-icon-edit" circle></el-button>
				</el-tooltip>
				<el-tooltip class="item" effect="dark" content="删除食谱" placement="top">
				    <el-button type="danger" icon="el-icon-delete" circle></el-button>
				</el-tooltip>
			</el-table-column> -->
		</el-table>
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
				input1:'',
				pagenum:1,
				seasontuijianList:[],
				total:0,
				all:true,
				search:false,
				dialogFormVisible:false,
				form:{
					season:'',
					cookbook:'',
					method:'',
				},
				dialogFormVisible1:false
			}
		},
		methods: {
			getSeasonTuiJianList(val){
				var that = this
				that.all = true
				that.search = false
				this.$http.post('http://dvwbxngn.dnat.tech/getAllSersonTuiJian',{
					pagenum:val
				})
				.then(function(res){
					console.log(res)
					that.seasontuijianList = res.data.seasontuijianList
					that.total = res.data.total
				})
				.catch(function(err){
					console.log(err)
				})
			},
			startsearch(val){
				var that = this
				this.$http.post('http://dvwbxngn.dnat.tech/searchSeasonTuiJian',{
					pagenum:val,
					search:that.input
				})
				.then(function(res){
					that.seasontuijianList = res.data.seasontuijianList,
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
			adddish(){
				var that = this
				this.$http.post('http://dvwbxngn.dnat.tech/addSeasonTuiJian',{
					season:that.form.season,
					cookbook:that.form.cookbook,
					method:that.form.method
				})
				.then(function(res){
					if(res.data.code == -1){
						that.$message.error(res.data.msg);
					}
					else {
						that.dialogFormVisible = !that.dialogFormVisible
						this.getUserList(1)
					}
				})
				.catch(function(err){
					console.log(err)
				})
			},
			deletedish(){
				var that = this
				this.$http.post('http://dvwbxngn.dnat.tech/deleteSeasonTuiJian',{
					cookbook:that.seasontuijianList[that.input1-1].cookbook
				})
				.then(function(res){
					if(res.data.code == -1){
						that.$message.error(res.data.msg);
					}
					else {
						that.dialogFormVisible1 = !that.dialogFormVisible
						this.getUserList(1)
					}
				})
				.catch(function(err){
					console.log(err)
				})
			},
			handleCurrentChange(val){
				var that = this
				if(that.all){
					this.getSeasonTuiJianList(val)
				}
				else {
					this.startsearch(val)
				}
			}
		},
		created(){
			this.getSeasonTuiJianList(1)
		}
	}
</script>

<style>
</style>
