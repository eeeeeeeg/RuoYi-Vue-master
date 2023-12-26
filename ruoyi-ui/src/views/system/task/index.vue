<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="88px"
    >
      <el-form-item label="站点" prop="siteId">
        <!-- <el-input
          v-model="queryParams.siteId"
          placeholder="请输入站点"
          clearable
          @keyup.enter.native="handleQuery"
        /> -->
        <el-select
          clearable
          v-model="queryParams.siteId"
          placeholder="请选择任务分配的站点"
          style="width: 100%"
        >
          <el-option
            v-for="item in siteOptions"
            :key="item.id"
            :value="item.id"
            :label="item.name"
            >{{ item.name }}</el-option
          >
        </el-select>
      </el-form-item>
      <el-form-item label="角色" prop="roleId">
        <!-- <el-input
          v-model="queryParams.roleId"
          placeholder="请输入角色"
          clearable
          @keyup.enter.native="handleQuery"
        /> -->
        <el-select
          clearable
          v-model="queryParams.roleId"
          placeholder="请选择角色"
          style="width: 100%"
        >
          <el-option
            v-for="item in roleOptions"
            :key="item.roleId"
            :value="item.roleId"
            :label="item.roleName"
            >{{ item.roleName }}</el-option
          >
        </el-select>
      </el-form-item>
      <el-form-item label="任务名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入任务名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="创建人" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入创建人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker
          clearable
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间"
        >
        </el-date-picker>
      </el-form-item> -->
      <!-- <el-form-item label="执行的角色" prop="updateBy">
        <el-input
          v-model="queryParams.updateBy"
          placeholder="请选择执行任务的角色"
          clearable
          @keyup.enter.native="handleQuery"
        />
        
      </el-form-item> -->
      <el-form-item label="任务状态" prop="status">
        <el-select
          clearable
          v-model="queryParams.status"
          placeholder="请选择任务状态"
          style="width: 100%"
        >
          <el-option value="1" label="未执行"></el-option>
          <el-option value="2" label="正在执行"></el-option>
          <el-option value="3" label="已完成"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="任务类型" prop="taskType">
        <el-select
          clearable
          v-model="queryParams.taskType"
          placeholder="请选择任务类型"
          style="width: 100%"
        >
          <el-option value="1" label="日巡检任务"></el-option>
          <el-option value="2" label="周巡检任务"></el-option>
          <el-option value="3" label="月巡检任务"></el-option>
          <el-option value="4" label="季度巡检任务"></el-option>
          <el-option value="5" label="年巡检任务"></el-option>
          <el-option value="6" label="专项巡检任务"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:task:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:task:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:task:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:task:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="taskList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="任务名称"
        align="center"
        min-width="240"
        prop="name"
      />
      <el-table-column label="任务状态" align="center" prop="status">
        <template slot-scope="scope">
          <el-tag class="ml-2" v-if="scope.row.status == '1'">未执行</el-tag>
          <el-tag
            class="ml-2"
            type="warning"
            v-else-if="scope.row.status == '2'"
            >正在执行</el-tag
          >
          <el-tag class="ml-2" type="success" v-else>已完成</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="站点"
        align="center"
        min-width="160"
        prop="siteId"
      >
        <template slot-scope="scope">
          {{ scope.row.siteId | filterSiteName }}
        </template>
      </el-table-column>
      <el-table-column label="角色" align="center" prop="roleId">
        <template slot-scope="scope">
          {{ scope.row.roleId | filterRoleName }}
        </template>
      </el-table-column>
      <el-table-column
        label="任务类型"
        align="center"
        min-width="120"
        prop="taskType"
      >
        <template slot-scope="scope">
          <div>
            {{ scope.row.taskType | filterTaskType }}
          </div>
        </template>
      </el-table-column>
      <el-table-column
        label="任务开始时间"
        align="center"
        prop="taskBeginTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.taskBeginTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="任务结束时间"
        align="center"
        prop="taskEndTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.taskEndTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建人" align="center" prop="createBy" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人" align="center" prop="updateBy" />
      <el-table-column
        label="更新时间"
        align="center"
        prop="updateTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>

      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column
        label="操作"
        align="center"
        min-width="120"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:task:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:task:remove']"
            >删除</el-button
          >
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            >查看任务表单</el-button
          > -->
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改zs_task对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="80%"
      append-to-body
      destroy-on-close
    >
      <TaskAdd
        v-if="open"
        :formInit="formInit"
        @cancel="cancel"
        @getList="handleQuery"
      ></TaskAdd>
    </el-dialog>
  </div>
</template>

<script>
import {
  listTask,
  getTask,
  delTask,
  addTask,
  updateTask,
} from "@/api/system/task";
import TaskAdd from "./TaskAdd.vue";

import { listRole } from "@/api/system/role";
import { listSite } from "@/api/system/site";
var that;
export default {
  name: "Task",
  components: { TaskAdd },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // zs_task表格数据
      taskList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        siteId: null,
        roleId: null,
        taskType: null,
        taskBeginTime: null,
        taskEndTime: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        name: null,
        formData: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      formInit: null,
      roleOptions: [],
      siteOptions: [],
    };
  },
  created() {
    that = this;
    this.getList();
    this.initOptionsList();
  },
  methods: {
    initOptionsList() {
      listRole({}).then((response) => {
        this.roleOptions = response.rows;
      });
      listSite({}).then((response) => {
        this.siteOptions = response.rows;
      });
    },
    /** 查询zs_task列表 */
    getList() {
      this.loading = true;
      listTask(this.queryParams).then((response) => {
        this.taskList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        siteId: null,
        roleId: null,
        taskType: null,
        taskBeginTime: null,
        taskEndTime: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        name: null,
        formData: null,
        status: null,
        remark: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.formInit = null;
      // this.reset();
      // this.$router.push({ path: "/system/system/task/add" });
      this.open = true;
      this.title = "添加任务";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.formInit = row;
      this.reset();
      // const id = row.id || this.ids;
      this.open = true;

      this.title = "修改任务";
      // getTask(id).then((response) => {
      //   this.form = response.data;
      //   this.open = true;
      // });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateTask(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTask(this.form).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认删除zs_task编号为"' + ids + '"的数据项？')
        .then(function () {
          return delTask(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/task/export",
        {
          ...this.queryParams,
        },
        `task_${new Date().getTime()}.xlsx`
      );
    },
  },
  filters: {
    filterTaskType(val) {
      let result = "";
      switch (val) {
        case "1":
          result = "日巡检任务";
          break;
        case "2":
          result = "周巡检任务";
          break;
        case "3":
          result = "月巡检任务";
          break;
        case "4":
          result = "季度巡检任务";
          break;
        case "5":
          result = "年巡检任务";
          break;
        case "6":
          result = "专项巡检任务";
          break;

        default:
          break;
      }
      return result;
    },
    filterRoleName(val) {
      const _temp = that.roleOptions.filter((item) => {
        return item.roleId == val;
      });
      if (_temp.length > 0) {
        return _temp[0].roleName;
      }
      return "--";
    },
    filterSiteName(val) {
      if (!val) {
        return "--";
      }
      const sites = val.split(",");
      const _temp = that.siteOptions
        .filter((item) => {
          return sites.includes(item.id + "");
        })
        .map((item) => item.name);
      return _temp.join(",");
    },
  },
};
</script>
