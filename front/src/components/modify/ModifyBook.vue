<template>
  <div>
    <head-top></head-top>
    <el-card class="box-card">
      <el-row :gutter="25">
        <el-col :span="10">
          <el-input v-model="searchInfo"
                    clearable
                    placeholder="查询图书(根据书名、作者、出版社)">
            <el-button slot="append"
                       icon="el-icon-search"
                       @click="searchBook"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-dropdown split-button
                       type="primary"
                       trigger="click"
                       @command="handleCommand">
            图书类型
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item :command="0">全部图书</el-dropdown-item>
              <el-dropdown-item :command="item.id"
                                v-for="item in bookType"
                                :key="item.id">{{item.name}}</el-dropdown-item>

            </el-dropdown-menu>
          </el-dropdown>
        </el-col>
      </el-row>
      <!-- 用户列表 -->
      <el-table :data="allBook"
                border
                stripe>
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left"
                     class="demo-table-expand">
              <el-form-item label="书籍介绍:">
                <span>{{ props.row.introduction }}</span>
              </el-form-item>
              <el-form-item label="出版日期:">
                <span>{{ props.row.pubDate | formatDate}}</span>
              </el-form-item>
              <el-form-item label="书籍价格:">
                <span>{{ props.row.price }}</span>
              </el-form-item>
              <el-form-item label="剩余数量:">
                <span>{{ props.row.number }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column label="书名"
                         prop="name"
                         width="266"></el-table-column>
        <el-table-column label="作者"
                         prop="author"
                         width="266"></el-table-column>
        <el-table-column label="出版社"
                         prop="publish"></el-table-column>
        <el-table-column label="ISBN"
                         prop="isbn"
                         width="220"></el-table-column>
        <el-table-column label="类型"
                         prop="type"
                         width="120"></el-table-column>
        <el-table-column label="语言"
                         prop="language"
                         width="120"></el-table-column>
        <el-table-column label="操作"
                         fixed="right"
                         width="150">
          <template slot-scope="scope">
            <!-- 详情 -->
            <el-button type="primary"
                       icon="el-icon-edit"
                       circle
                       @click="edit(scope.row)">修改</el-button>

          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog title="是否修改"
               :visible.sync="editBookDialog"
               top="4vh"
               center>
      <el-form ref="ruleForm"
               :rules="rules"
               label-width="120px"
               class="demo-ruleForm">
        <el-form-item label="书名"
                      prop="name">
          <el-input v-model="oneBook.name"
                    :placeholder="oneBook.name"
                    clearable></el-input>
        </el-form-item>
        <el-form-item label="作者"
                      prop="author">
          <el-input v-model="oneBook.author"
                    :placeholder="oneBook.author"
                    clearable></el-input>
        </el-form-item>
        <el-form-item label="出版社"
                      prop="publish">
          <el-input v-model="oneBook.publish"
                    :placeholder="oneBook.publish"
                    clearable></el-input>
        </el-form-item>
        <el-form-item label="ISBN"
                      prop="isbn">
          <el-input v-model="oneBook.isbn"
                    :placeholder="oneBook.isbn"
                    clearable></el-input>
        </el-form-item>
        <el-form-item label="数量"
                      prop="number">
          <el-input-number v-model="oneBook.number"
                           :placeholder="oneBook.number"
                           :min="1"
                           :max="123456789"></el-input-number>
        </el-form-item>
        <el-form-item label="原价"
                      prop="price">
          <el-input-number v-model="oneBook.price"
                           :placeholder="oneBook.price"
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
                              v-model="oneBook.pubDate"
                              :placeholder="oneBook.pubDate"
                              style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="书籍类型"
                      prop="type">
          <el-select v-model="oneBook.type"
                     :placeholder="oneBook.type">
            <el-option v-for="(item,index) in bookType"
                       :key="(item,index)"
                       :label="item.name"
                       :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="语言"
                      prop="language">
          <el-radio-group v-model="oneBook.language"
                          :placeholder="oneBook.language">
            <el-radio label="中文"></el-radio>
            <el-radio label="英文"></el-radio>
            <el-radio label="法文"></el-radio>
            <el-radio label="俄文"></el-radio>
            <el-radio label="德文"></el-radio>
            <el-radio label="日文"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="书籍介绍"
                      prop="introduction">
          <el-input type="textarea"
                    rows="8"
                    cols="5"
                    v-model="oneBook.introduction"
                    :placeholder="oneBook.introduction">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary"
                     @click="update()">修改</el-button>
          <el-button @click="editBookDialog=false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
import HeadTop from '@/components/manage/ManageHeadTop'

import { formatDate } from '@/assets/js/date'
export default {
  filters: {
    formatDate (time) {
      var date = new Date(time)
      return formatDate(date, 'yyyy-MM-dd hh:mm')
    }
  },
  components: { HeadTop },
  data () {
    return {
      bookType: [],
      allBook: [],
      oneBook: [],
      // 控制详情弹框显示/隐藏
      editBookDialog: false,
      searchInfo: '',
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
  // onload事件
  created () {
    // 查询用户id
    this.getBookTypeList()
    this.getAllBook()
  },
  methods: {
    async getAllBook () {
      const { data: res } = await this.$http.get('searchAllBook?info=')
      if (res.message == 'success') {
        this.allBook = res.data.searchAllBookList
      } else {
        this.$message.error('获取书籍失败')
      }
    },
    async getBookTypeList () {
      const { data: res } = await this.$http.get('bookTypeList?')
      if (res.message == 'success') {
        this.bookType = res.data.bookTypeList
      } else {
        this.$message.error('获取列表失败')
      }
    },
    async searchBook () {
      const { data: res } = await this.$http.get('searchAllBook?info=' + this.searchInfo)
      if (res.message == 'success') {
        this.allBook = res.data.searchAllBookList
      } else {
        this.$message.error('获取书籍失败')
      }
    },
    async handleCommand (typeId) {
      if (!typeId) {
        this.getAllBook()
        return
      }
      const { data: res } = await this.$http.get('oneTypeBook?typeId=' + typeId)
      if (res.message == 'success') {
        console.log(1234565)
        this.allBook = res.data.oneTypeBookList
      } else {
        this.$message.error('获取书籍详情失败')
      }
    },
    edit (item) {
      console.log(item)
      this.oneBook = {
        name: item.name,
        author: item.author,
        publish: item.publish,
        isbn: item.isbn,
        language: item.language,
        pubDate: item.pubDate,
        number: item.number,
        price: item.price,
        introduction: item.introduction,
        type: item.type,
        id: item.id
      }
      this.editBookDialog = true
    },
    async update () {
      this.editBookDialog = false
      const { data: res } = await this.$http.post('modifyBook', {
        id: this.oneBook.id,
        name: this.oneBook.name,
        author: this.oneBook.author,
        publish: this.oneBook.publish,
        isbn: this.oneBook.isbn,
        language: this.oneBook.language,
        pubDate: this.oneBook.pubDate,
        number: this.oneBook.number,
        price: this.oneBook.price,
        introduction: this.oneBook.introduction,
        type: this.oneBook.type
      })
      if (res.message == 'success') {
        this.$message.success('修改书籍信息成功')
      } else {
        this.$message.error('获取书籍失败')
      }
    }
  }
}
</script>
<style>
</style>
