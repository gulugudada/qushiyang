<template>
	<el-card>
		<el-col>
			<el-input style="width: 400px;"
				placeholder="请输入体质或食谱名"
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
			<el-form-item label="体质" :label-width="formLabelWidth">
		      <el-input v-model="form.physqiue" autocomplete="off"></el-input>
		    </el-form-item>
			<el-form-item label="食谱名" :label-width="formLabelWidth">
			  <el-input v-model="form.dishname" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="食材" :label-width="formLabelWidth">
			  <el-input v-model="form.stock" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="做法" :label-width="formLabelWidth">
			  <el-input v-model="form.mothde" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="功效" :label-width="formLabelWidth">
			  <el-input v-model="form.effect" autocomplete="off"></el-input>
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
					<el-button @click="dialogFormVisible = false">取 消</el-button>
					<el-button type="primary" @click="deletedish()">确 定</el-button>
			</div>
			</el-form>
		</el-dialog>
		<!-- 渲染表格请求 -->
		<el-table
		    :data="dishList"
		    border
		    style="width: 100%">
		    <el-table-column
				type="index"
				width="60">
		    </el-table-column>
			<el-table-column
				prop="physique"
				label="体质"
				width="70">
			</el-table-column>
		    <el-table-column
				prop="dish.dishname"
				label="食谱名"
				width="100">
		    </el-table-column>
			<el-table-column
				prop="dish.stock"
				label="食材">
		    </el-table-column>
			<el-table-column
				prop="dish.method"
				label="做法"
				width="400">
			</el-table-column>
			<el-table-column
				prop="dish.effect"
				label="功效"
				width="160">
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
				page-size="3"
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
				dishList:[],
				pagenum:1,
				total:0,
				all:true,
				search:false,
				dialogFormVisible:false,
				form:{
					physqiue:'',
					dishname:'',
					stock:'',
					mothde:'',
					effect:'',
				},
				dialogFormVisible1:false
			}
		},
		methods: {
			findAllPhysiqueCookbook(val){
				var that = this
				that.all = true
				that.search = false
				this.$http.post('http://dvwbxngn.dnat.tech/getAllPhysiqueCookbook',{
					pagenum:val
				})
				.then(function(res){
					that.dishList = res.data.cookbookList
					that.total = res.data.total
				})
				.catch(function(err){
					console.log(err)
				})
			},
			startsearch(val){
				var that = this
				this.$http.post('http://dvwbxngn.dnat.tech/searchPhysiqueCookbook',{
					pagenum:val,
					search:that.input
				})
				.then(function(res){
					that.userList = res.data.accountList,
					that.total = res.data.total
				})
				.catch(function(err){
					console.log(err)
				})
			},
			adddish(){
				
			},
			deletedish(){
				
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
					this.findAllPhysiqueCookbook(val)
				}
				else {
					this.startsearch(val)
				}
			}
		},
		created(){
			this.findAllPhysiqueCookbook(1)
		}
	}
</script>

<style>
</style>
