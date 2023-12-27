<template>
  <div class="app-container">
    <el-form
      :model="form"
      ref="formRef"
      size="small"
      :rules="rules"
      label-width="135px"
    >
      <el-form-item label="任务" prop="name">
        <el-input v-model="form.name" placeholder="请输入任务名称" clearable />
      </el-form-item>
      <el-form-item label="任务类型" prop="taskType">
        <el-select
          clearable
          v-model="form.taskType"
          placeholder="请选择任务类型"
          style="width: 100%"
        >
          <el-option
            v-for="item in taskType"
            :key="item.id"
            :value="item.id"
            :label="item.name"
            >{{ item.name }}</el-option
          >
        </el-select>
      </el-form-item>
      <el-form-item label="站点" prop="siteIds">
        <el-select
          clearable
          v-model="form.siteId"
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
      <el-form-item label="角色" prop="roleIds">
        <el-select
          clearable
          v-model="form.roleId"
          placeholder="可多选"
          multiple
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
      <el-form-item label="备注" prop="remark">
        <el-input
          v-model="form.remark"
          type="textarea"
          placeholder="请输入内容"
        />
      </el-form-item>
      <el-form-item label="表单信息维护" prop="formData">
        <el-button @click="handleAddNewForm" type="primary"
          >表单信息维护</el-button
        >
      </el-form-item>
      <el-form-item label="自动生成定时任务" prop="ifGenertTime">
        <el-radio-group v-model="form.ifGenertTime" class="ml-4">
          <el-radio :label="true">是</el-radio>
          <el-radio :label="false">否</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="立即创建任务" prop="ifExe" v-if="form.ifGenertTime">
        <el-radio-group v-model="form.ifExe" class="ml-4">
          <el-radio :label="true">是</el-radio>
          <el-radio :label="false">否</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <div slot="footer" class="add-page-footer">
      <el-button type="primary" @click="submitForm">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>
    <!-- 添加或修改zs_task对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      fullscreen
      append-to-body
      destroy-on-close
    >
      <FormGen ref="formGenRef"></FormGen>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormDialog">确 定</el-button>
        <el-button @click="cancelDialog">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRole } from "@/api/system/role";
import { listSite } from "@/api/system/site";

import { addTask, updateTask } from "@/api/system/task";
import FormGen from "../../tool/build/index.vue";
export default {
  name: "TaskAdd",
  components: { FormGen },
  props: {
    formInit: {
      type: Object,
      default: () => ({}),
    },
  },
  data() {
    return {
      form: {
        name: "", // 任务名称
        siteId: "", // 站点id
        roleId: [], // 角色id ,List
        formData: [], // form组件list
        remark: "",
        ifGenertTime: null,
        ifExe: true,
      },
      siteOptions: [], // 站点下拉选择列表
      roleOptions: [], // 角色下拉选择列表
      drawingList: [],
      title: "",
      open: false,
      rules: {
        name: [{ required: true, trigger: "blur", message: "请输入任务名称" }],
        siteId: [{ required: true, trigger: "blur", message: "请选择站点" }],
        roleId: [{ required: true, trigger: "blur", message: "请选择角色" }],
        taskType: [
          { required: true, trigger: "blur", message: "请选择任务类型" },
        ],
        formData: [
          {
            required: true,
            trigger: "blur",
            message: "请编辑您的任务表单",
            trigger: "change",
          },
        ],
        ifGenertTime: [
          {
            required: true,
            trigger: "blur",
            message: "请选择是否生成定时任务",
          },
        ],
        ifExe: [
          { required: true, trigger: "blur", message: "请选择是否立即执行" },
        ],
      },
      taskType: [
        { id: "1", name: "日" },
        { id: "2", name: "周" },
        { id: "3", name: "月" },
        { id: "4", name: "季度" },
        { id: "5", name: "年" },
        { id: "6", name: "专项" },
      ],
    };
  },
  methods: {
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
    /** 提交按钮 */
    submitForm() {
      this.$refs["formRef"].validate((valid) => {
        if (valid) {
          const params = {
            id: this.form.id,
            name: this.form.name,
            siteId: this.form.siteId,
            roleId: this.form.roleId.join(","),
            formData: JSON.stringify(this.form.formData),
            remark: this.form.remark,
            taskType: this.form.taskType,
            ifGenertTime: this.form.ifGenertTime,
            ifExe: this.form.ifGenertTime ? this.form.ifExe : true,
          };
          if (this.form.id != null) {
            updateTask(params).then(() => {
              this.$modal.msgSuccess("修改成功");
              // this.$router.back();
              this.$emit("cancel");
              this.$emit("getList");
            });
          } else {
            addTask(params).then(() => {
              this.$modal.msgSuccess("新增成功");
              // this.$router.back();
              this.$emit("cancel");
              this.$emit("getList");
            });
          }
        }
      });
    },
    //维护当前表单信息
    handleAddNewForm() {
      this.open = true;
      this.title = "维护任务所需表单";
    },
    // 初始话下拉列表值
    initOptionsList() {
      listRole({}).then((response) => {
        this.roleOptions = response.rows;
      });
      listSite({}).then((response) => {
        this.siteOptions = response.rows;
      });
    },

    cancel() {
      // 取消会清空当前编辑的表单树
      this.$emit("cancel");
    },
    submitFormDialog() {
      this.open = false;
      this.form.formData = this.$refs.formGenRef?.drawingList;
      console.log(this.form.formData);
    },
    cancelDialog() {
      // 取消会清空当前编辑的表单树
      this.open = false;
      this.form.drawingList = [];
    },
  },
  mounted() {
    console.log("init");
    console.log(this.formInit);
    this.initOptionsList();
    if (this.formInit) {
      this.form = {
        ...this.formInit,
        roleId: this.formInit?.roleId.split(",").map((item) => parseInt(item)),
      };
    }
  },
};
</script>
<style scoped>
::v-deep .el-dialog__body {
  padding: 0px 20px;
}
.add-page-footer {
  width: 100%;
  text-align: right;
}
</style>
