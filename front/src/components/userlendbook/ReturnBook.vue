<template>
  <div>
    <head-top></head-top>
    <el-card>
      <el-row :gutter="25">
        <el-col :span="10">
          <el-input v-model="searchInfo"
                    clearable
                    placeholder="查询图书(书名、作者、出版社)">
            <el-button slot="append"
                       icon="el-icon-search"
                       @click="searchSingleLendBook"></el-button>
          </el-input>
        </el-col>
      </el-row>
      <!-- 用户列表 -->
      <el-table :data="lendBookList"
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
              <el-form-item label="书籍类型:">
                <span>{{ props.row.type }}</span>
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
        <el-table-column label="语言"
                         prop="language"
                         width="220"></el-table-column>
        <el-table-column label="价格"
                         prop="price"
                         width="120"></el-table-column>
        <el-table-column label="操作"
                         fixed="right"
                         width="150">
          <template slot-scope="scope">
            <el-button type="success"
                       size="mini"
                       @click="returnBookDialog(scope.row)">归还</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-dialog title="是否归还"
                 :visible.sync="returnBookDialogVisible"
                 width="25%"
                 top="4vh"
                 center>
        <el-form label-position="left"
                 :data="lendBookInfo">
          <el-form-item label="书名 :">
            <span>{{lendBookInfo.name}}</span>
          </el-form-item>
          <el-form-item label="作者 :">
            <span>{{lendBookInfo.author}}</span>
          </el-form-item>
          <el-form-item label="出版社 :">
            <span>{{lendBookInfo.publish}}</span>
          </el-form-item>
          <el-form-item label="语言 :">
            <span>{{lendBookInfo.language}}</span>
          </el-form-item>
          <el-form-item label="ISBN :">
            <span>{{lendBookInfo.isbn}}</span>
          </el-form-item>
        </el-form>
        <span slot="footer"
              class="dialog-footer">
          <el-button @click="returnBookDialogVisible = false">取 消</el-button>
          <el-button type="primary"
                     @click="returnBook(lendBookInfo.id)">确 定</el-button>
        </span>
      </el-dialog>
    </el-card>
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
  components: {
    HeadTop
  },
  created () {
    this.getLendList()
  },
  data () {
    return {
      lendBookList: [],
      lendBookInfo: '',
      returnBookDialogVisible: false,
      searchInfo: ''
    }
  },
  methods: {
    returnBookDialog (bookInfo) {
      this.returnBookDialogVisible = true
      this.lendBookInfo = bookInfo
    },
    async getLendList () {
      const { data: res } = await this.$http.get('searchLendBook?info=')
      if (res.message == 'success') {
        this.lendBookList = res.data.searchLendBookList
      } else {
        this.$message.error('服务器出错')
      }
    },
    async returnBook (bookId) {
      this.returnBookDialogVisible = false
      const { data: res } = await this.$http.post('returnBook', {
        'bookId': bookId
      })
      if (res.message == 'success') {
        this.$message.success('归还成功')
        this.getLendList()
      } else {
        this.$message.error('服务器出错')
      }
    },
    async searchSingleLendBook () {
      const { data: res } = await this.$http.get('searchLendBook?info=' + this.searchInfo)
      if (res.message == 'success') {
        this.lendBookList = res.data.searchLendBookList
      } else {
        this.$message.error('查询图书失败')
      }
    }
  }
}
</script>
<style lang="less" scoped>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>
