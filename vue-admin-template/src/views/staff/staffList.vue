<template>
  <div>
    <el-form style="margin: 20px" :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item label="">
        <el-input v-model="formInline.empName" placeholder="姓名"/>
      </el-form-item>
      <el-form-item label="">
        <el-select v-model="formInline.deptName" placeholder="部门">
          <el-option label="业务部" value="业务部" />
          <el-option label="人事部" value="人事部" />
          <el-option label="后勤部" value="后勤部" />
        </el-select>
      </el-form-item>
      <el-form-item label="">
        <el-select v-model="formInline.empDegreeName" placeholder="学历">
          <el-option label="本科" value="本科" />
          <el-option label="大专" value="大专" />
          <el-option label="研究生" value="研究生" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
        <el-button type="primary" @click="onSubmit">查询</el-button>
        <el-button type="primary" @click="onSave">新增</el-button>
        <el-button type="primary" @click="onDownload">导出</el-button>
      </el-form-item>
    </el-form>

    <el-table
      :data="tableData"
      border
      style="width: 80%; margin: 20px"
    >
      <el-table-column fixed sortable prop="id" label="序号" width="150" />
      <el-table-column prop="empName" label="姓名" width="150" />
      <el-table-column prop="sex" label="性别" width="120" />
      <el-table-column prop="age" label="年龄" width="120" />
      <el-table-column prop="deptName" label="部门" width="120" />
      <el-table-column prop="empDegreeName" label="学历" width="300" />
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      class="container"
      small
      layout="prev, pager, next"
      :page-count="pages"
      @prev-click="currentChange"
      @current-change="currentChange"
      @next-click="currentChange"
    />

    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose"
    >
      <el-form style="margin: 20px" :inline="true" :model="updateData" class="demo-form-inline">
        <el-form-item label="">
          <el-input v-model="updateData.age" placeholder="年龄" />
        </el-form-item>
        <el-form-item label="">
          <el-select v-model="updateData.sex" placeholder="性别">
            <el-option label="男" value="男" />
            <el-option label="女" value="女" />
          </el-select>
        </el-form-item>
        <el-form-item label="">
          <el-input v-model="updateData.empName" placeholder="姓名" />
        </el-form-item>
        <el-form-item label="">
          <el-select v-model="updateData.deptName" placeholder="部门">
            <el-option label="业务部" value="业务部" />
            <el-option label="人事部" value="人事部" />
            <el-option label="后勤部" value="后勤部" />
          </el-select>
        </el-form-item>
        <el-form-item label="">
          <el-select v-model="updateData.empDegreeName" placeholder="学历">
            <el-option label="本科" value="本科" />
            <el-option label="大专" value="大专" />
            <el-option label="研究生" value="研究生" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onUpdate">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
import { staffDownload, saveStaff, deleteStaff, updateStaff, selectStaffPage } from '@/api/staff'

export default {
  data() {
    return {
      dialogVisible: false,
      pages: 0,
      formInline: {
        current: null,
        size: null,
        empName: '',
        deptName: '',
        empDegreeName: ''
      },
      updateData: {
        id: null,
        age: null,
        deptName: '',
        sex: '',
        empName: '',
        empDegreeName: ''
      },
      tableData: []
    }
  },
  beforeMount() {
    this.selectStaff()
  },
  methods: {
    resetForm() {
      this.formInline = {}
      this.selectStaff()
    },
    onSubmit() {
      this.selectStaff()
    },
    onDownload() {
      window.location.href = 'http://localhost:8080/staff/download'
      this.$message.success('导出成功')
    },
    selectStaff() {
      selectStaffPage(this.formInline).then((res) => {
        this.tableData = res.data.records
        this.pages = res.data.pages
      }).catch(() => {
        this.loading = false
      })
    },
    onUpdate() {
      if (this.updateData.id) {
        updateStaff(this.updateData).then((res) => {
          this.$message({
            message: '修改成功',
            type: 'success'
          })
        }).catch(() => {
          this.loading = false
        })
        this.dialogVisible = false
        this.updateData = {}
        selectStaffPage(this.formInline).then((res) => {
          this.tableData = res.data.records
          this.pages = res.data.pages
        }).catch(() => {
          this.loading = false
        })
      } else {
        this.saveStaffData()
        this.selectStaff()
      }
    },
    onSave() {
      this.updateData = {}
      this.dialogVisible = true
    },
    saveStaffData() {
      saveStaff(this.updateData).then((res) => {
        this.$message({
          message: '新增成功',
          type: 'success'
        })
      }).catch(() => {
        this.loading = false
      })
      this.dialogVisible = false
      this.updateData = {}
    },
    handleDelete(event, row) {
      this.updateData.id = row.id
      deleteStaff(this.updateData).then(() => {
        selectStaffPage(this.formInline).then((res) => {
          this.tableData = res.data.records
          this.pages = res.data.pages
        }).catch(() => {
          this.loading = false
        })
        this.$message({
          message: '删除成功',
          type: 'success'
        })
      }).catch(() => {
        this.loading = false
      })
    },
    handleEdit(event, row) {
      this.dialogVisible = true
      this.updateData = { ...row }
    },
    currentChange(event) {
      this.formInline.current = event
      this.selectStaff()
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {})
    }
  }
}
</script>
<style scoped lang="scss">
.container {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
