<template>
  <div>
    <head-top></head-top>
    <el-form :model="ruleForm"
             :rules="rules"
             ref="ruleForm"
             label-width="100px"
             style="width: 50%; "
             class="demo-ruleForm">
      <el-form-item label="书名"
                    prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="作者"
                    prop="author">
        <el-input v-model="ruleForm.author"></el-input>
      </el-form-item>
      <el-form-item label="出版社"
                    prop="publish">
        <el-input v-model="ruleForm.publish"></el-input>
      </el-form-item>
      <el-form-item label="ISBN"
                    prop="isbn">
        <el-input v-model="ruleForm.isbn"></el-input>
      </el-form-item>
      <el-form-item label="数量"
                    prop="number">
        <el-input-number v-model="ruleForm.number"
                         :min="1"
                         :max="123456789"></el-input-number>
      </el-form-item>
      <el-form-item label="原价"
                    prop="price">
        <el-input-number v-model="ruleForm.price"
                         :min="1"
                         :precision="1"
                         :step="0.1"
                         :max="11111"></el-input-number>
      </el-form-item>
      <el-form-item label="出版日期"
                    required>
        <el-col :span="11">
          <el-form-item prop="pubDate">
            <el-date-picker type="date"
                            placeholder="选择日期"
                            v-model="ruleForm.pubDate"
                            style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>
      </el-form-item>
      <el-form-item label="书籍类型"
                    prop="type">
        <el-select v-model="ruleForm.type"
                   placeholder="请选择书籍类型">
          <el-option v-for="(item,index) in bookType"
                     :key="(item,index)"
                     :label="item.typeName"
                     :value="item.typeId"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="语言"
                    prop="language">
        <el-radio-group v-model="ruleForm.language">
          <el-radio label="中文"></el-radio>
          <el-radio label="英文"></el-radio>
          <el-radio label="法文"></el-radio>
          <el-radio label="俄文"></el-radio>
          <el-radio label="德文"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="书籍介绍"
                    prop="introduction">
        <el-input type="textarea"
                  rows="8"
                  cols="5"
                  v-model="ruleForm.introduction">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary"
                   @click="submitForm('ruleForm')">立即添加</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import HeadTop from '../HeadTop'
export default {
  components: {
    HeadTop
  },
  data () {
    return {
      bookType: [],
      ruleForm: {
        name: '',
        author: '',
        publish: '',
        isbn: '',
        type: '',
        language: [],
        price: '',
        pubDate: '',
        number: '',
        introduction: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入书名', trigger: 'blur' }
        ],
        author: [
          { required: true, message: '请输入作者', trigger: 'blur' }
        ],
        publish: [
          { required: true, message: '请输入出版社', trigger: 'blur' }
        ],
        isbn: [
          { required: true, message: '请输入ISBN', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请选择类型', trigger: 'blur' }
        ],
        pubDate: [
          { required: true, message: '请选择出版日期', trigger: 'blur' }
        ],
        language: [
          { required: true, message: '请选择语言', trigger: 'blur' }
        ]
      }
    }
  },
  created () {
    // 查询用户id
    this.getBookType()
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate(async (valid) => {
        if (!valid) {
          console.log('error submit!!')
          return false
        }
        const { data: res } = await this.$http.post('addBook', this.ruleForm)
        if (res.message == 'success') {
          this.$message.success('书籍成功入库')
        } else {
          this.$message.error('服务器出错')
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    async getBookType () {
      const { data: res } = await this.$http.get('bookTypeList?')
      if (res.message == 'success') {
        this.bookType = res.data.bookTypeList
      } else {
        this.$message.error('获取列表失败')
      }
    }
  }
}
</script>
<style lang="less">
</style>
