<template>
  <div>
    <head-top></head-top>
    <el-card class="box-card">
      <el-row :gutter="25">
        <el-col :span="10">
          <el-input v-model="searchInfo"
                    clearable
                    placeholder="查询图书(书名、作者、出版社)">
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
              <el-form-item label="语言:">
                <span>{{ props.row.language }}</span>
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
        <el-table-column label="剩余数量"
                         prop="number"
                         width="120"></el-table-column>
        <el-table-column label="操作"
                         fixed="right"
                         width="150">
          <template slot-scope="scope">
            <!-- 借阅 -->
            <el-button :type="borrowBtnType(scope.row.state)"
                       size="mini"
                       :disabled="disableType(scope.row.state)"
                       @click="manageBook(scope.row)">{{scope.row.state}}</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog :title="dialogTitle"
               :visible.sync="borrowBookDialogVisible"
               top="4vh"
               center>
      <el-form label-position="left"
               :data="bookDetail">
        <el-form-item label="书名 :">
          <span>{{bookDetail.name}}</span>
        </el-form-item>
        <el-form-item label="作者 :">
          <span>{{bookDetail.author}}</span>
        </el-form-item>
        <el-form-item label="出版社 :">
          <span>{{bookDetail.publish}}</span>
        </el-form-item>
        <el-form-item label="ISBN :">
          <span>{{bookDetail.isbn}}</span>
        </el-form-item>
        <el-form-item label="价格 :">
          <span>{{bookDetail.price}}</span>
        </el-form-item>
        <el-form-item label="语言 :">
          <span>{{bookDetail.language}}</span>
        </el-form-item>
        <el-form-item label="出版日期 :">
          <span>{{bookDetail.pubDate}}</span>
        </el-form-item>
        <el-form-item label="书籍介绍 :">
          <span>{{bookDetail.introduction}}</span>
        </el-form-item>
      </el-form>
      <span slot="footer"
            class="dialog-footer">
        <el-button @click="borrowBookDialogVisible = false">取 消</el-button>
        <el-button type="primary"
                   @click="lendBook(bookDetail.id)">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import HeadTop from '../HeadTop'

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
      bookDetail: [],
      // 控制详情弹框显示/隐藏
      borrowBookDialogVisible: false,
      dialogTitle: '',
      searchInfo: ''
    }
  },
  // onload事件
  created () {
    // 查询用户id
    this.getBookTypeList()
    this.getAllBook()
  },
  methods: {
    borrowBtnType (state) {
      var btnType = 'primary'
      if (state == '归还') {
        btnType = 'success'
      } else if (state == '已空') {
        btnType = 'danger'
      }
      return btnType
    },
    disableType (state) {
      if (state == '已空') {
        return true
      }
      return false
    },
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
    async searchBook () {
      const { data: res } = await this.$http.get('searchAllBook?info=' + this.searchInfo)
      if (res.message == 'success') {
        console.log(1234565)
        this.allBook = res.data.searchAllBookList
      } else {
        this.$message.error('查询图书失败')
      }
    },
    manageBook (bookInfo) {
      this.bookDetail = bookInfo
      this.borrowBookDialogVisible = true
      if (bookInfo.state == '租借') {
        this.dialogTitle = '是否租借'
      } else if (bookInfo.state == '归还') {
        this.dialogTitle = '是否归还'
      }
    },
    async lendBook (bookId) {
      this.borrowBookDialogVisible = false
      console.log(bookId)
      if (this.dialogTitle == '是否租借') {
        const { data: res } = await this.$http.post('lendBook', {
          'bookId': bookId
        })
        if (res.message == 'success') {
          this.$message.success('租借成功')
          this.getAllBook()
        } else {
          this.$message.error('服务器出错')
        }
      } else {
        const { data: res } = await this.$http.post('returnBook', {
          'bookId': bookId
        })
        if (res.message == 'success') {
          this.$message.success('归还成功')
          this.getAllBook()
        } else {
          this.$message.error('服务器出错')
        }
      }
    }
  }
}
</script>
