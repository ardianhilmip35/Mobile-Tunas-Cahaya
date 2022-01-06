-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 28 Des 2021 pada 14.25
-- Versi server: 10.4.17-MariaDB
-- Versi PHP: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tunascahaya`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_detailpemesanan`
--

CREATE TABLE `tb_detailpemesanan` (
  `id_detail` tinyint(3) NOT NULL,
  `id_pemesanan` int(3) NOT NULL,
  `nama_bangunan` varchar(30) NOT NULL,
  `total_harga` int(10) NOT NULL,
  `cicilan` int(2) NOT NULL,
  `hrg_cicilan` int(10) NOT NULL,
  `tanggal_pembayaran` date NOT NULL,
  `bukti_pembayaran` mediumblob NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_detailpemesanan`
--

INSERT INTO `tb_detailpemesanan` (`id_detail`, `id_pemesanan`, `nama_bangunan`, `total_harga`, `cicilan`, `hrg_cicilan`, `tanggal_pembayaran`, `bukti_pembayaran`, `status`) VALUES
(3, 7, 'Masjid Hidayah', 12000000, 12, 1000000, '2021-12-18', '', 'Sukses'),
(4, 8, 'Masjid Anur', 10000000, 10, 10000000, '2021-12-22', '', 'Pending');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_jabatan`
--

CREATE TABLE `tb_jabatan` (
  `id_jabatan` tinyint(3) NOT NULL,
  `nama_jabatan` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_jabatan`
--

INSERT INTO `tb_jabatan` (`id_jabatan`, `nama_jabatan`) VALUES
(1, 'CEO'),
(2, 'Sekretaris I'),
(3, 'Sekretaris II'),
(4, 'Sekretaris III'),
(5, 'Bendahara I'),
(6, 'Bendahara II'),
(7, 'Bendahara III'),
(8, 'Arsitek I'),
(9, 'Arsitek II');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_karyawan`
--

CREATE TABLE `tb_karyawan` (
  `id_karyawan` tinyint(3) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `id_jabatan` tinyint(3) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(15) NOT NULL,
  `nomerhp` varchar(13) NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_karyawan`
--

INSERT INTO `tb_karyawan` (`id_karyawan`, `nama`, `id_jabatan`, `email`, `password`, `nomerhp`, `alamat`) VALUES
(2, 'Lihin A', 1, 'lihincak2@gmail.com', 'caklihin2', '0812345678', 'Sidoarjo'),
(3, 'Tanti Wulansari', 2, 'tantiwulansari2603@gmail.com', 'tanti123', '08293849291', 'Jonggoll'),
(7, 'Gugug', 3, 'gug@gmail.com', '12345', '08293849234', 'Rambi, Jember'),
(8, 'Ardian Hilmi', 4, 'ardian@gmail.com', '2345', '087765283746', 'Rambi Jember'),
(10, 'Tantan', 8, 'tantan@gmail.com', '12345', '08293849234', 'Sidoarjo');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_katalog`
--

CREATE TABLE `tb_katalog` (
  `id_katalog` varchar(5) NOT NULL,
  `nama_bangunan` varchar(30) NOT NULL,
  `Kategori` varchar(15) NOT NULL,
  `gambar` varchar(5000) NOT NULL,
  `deskripsi` text NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_katalog`
--

INSERT INTO `tb_katalog` (`id_katalog`, `nama_bangunan`, `Kategori`, `gambar`, `deskripsi`, `alamat`) VALUES
('K001', 'Masjid', 'Masjid', 'ytb.png', 'Masjid', 'Lumajang'),
('K002', 'Mall23', 'Mall', 'gmail.png', 'MallD', 'sby'),
('K003', 'Mall', 'Mall2', 'unnamed (1).jpg', 'yai tu lah', 'Lumajang');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_pemesanan`
--

CREATE TABLE `tb_pemesanan` (
  `id_pemesanan` int(3) NOT NULL,
  `id_pelanggan` tinyint(3) NOT NULL,
  `id_katalog` varchar(5) NOT NULL,
  `tgl_pemesanan` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_pemesanan`
--

INSERT INTO `tb_pemesanan` (`id_pemesanan`, `id_pelanggan`, `id_katalog`, `tgl_pemesanan`) VALUES
(7, 38, 'K001', '2021-12-13'),
(8, 37, 'K002', '2021-12-22');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_user`
--

CREATE TABLE `tb_user` (
  `id_pelanggan` tinyint(3) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `nomerhp` varchar(13) NOT NULL,
  `user_password` varchar(13) NOT NULL,
  `alamat_user` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_user`
--

INSERT INTO `tb_user` (`id_pelanggan`, `nama`, `email`, `nomerhp`, `user_password`, `alamat_user`) VALUES
(36, 'Galuh April', 'galuh123@gmail.com', '0824735325', '12345', 'Jonggol'),
(37, 'Tanti Wulansari', 'tantan@gmail.com', '087784666329', '123456', 'Randuagung Lumajang'),
(38, 'Azizah', 'Azizah@gmail.com', '087784666326', '4567', 'Dringu probolinggo');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tb_detailpemesanan`
--
ALTER TABLE `tb_detailpemesanan`
  ADD PRIMARY KEY (`id_detail`),
  ADD KEY `id_pemesanan` (`id_pemesanan`);

--
-- Indeks untuk tabel `tb_jabatan`
--
ALTER TABLE `tb_jabatan`
  ADD PRIMARY KEY (`id_jabatan`);

--
-- Indeks untuk tabel `tb_karyawan`
--
ALTER TABLE `tb_karyawan`
  ADD PRIMARY KEY (`id_karyawan`),
  ADD KEY `id_jabatan` (`id_jabatan`);

--
-- Indeks untuk tabel `tb_katalog`
--
ALTER TABLE `tb_katalog`
  ADD PRIMARY KEY (`id_katalog`);

--
-- Indeks untuk tabel `tb_pemesanan`
--
ALTER TABLE `tb_pemesanan`
  ADD PRIMARY KEY (`id_pemesanan`),
  ADD KEY `username` (`id_pelanggan`),
  ADD KEY `id_katalog` (`id_katalog`);

--
-- Indeks untuk tabel `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tb_detailpemesanan`
--
ALTER TABLE `tb_detailpemesanan`
  MODIFY `id_detail` tinyint(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `tb_jabatan`
--
ALTER TABLE `tb_jabatan`
  MODIFY `id_jabatan` tinyint(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `tb_karyawan`
--
ALTER TABLE `tb_karyawan`
  MODIFY `id_karyawan` tinyint(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT untuk tabel `tb_pemesanan`
--
ALTER TABLE `tb_pemesanan`
  MODIFY `id_pemesanan` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `tb_user`
--
ALTER TABLE `tb_user`
  MODIFY `id_pelanggan` tinyint(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `tb_detailpemesanan`
--
ALTER TABLE `tb_detailpemesanan`
  ADD CONSTRAINT `tb_detailpemesanan_ibfk_1` FOREIGN KEY (`id_pemesanan`) REFERENCES `tb_pemesanan` (`id_pemesanan`);

--
-- Ketidakleluasaan untuk tabel `tb_karyawan`
--
ALTER TABLE `tb_karyawan`
  ADD CONSTRAINT `tb_karyawan_ibfk_1` FOREIGN KEY (`id_jabatan`) REFERENCES `tb_jabatan` (`id_jabatan`);

--
-- Ketidakleluasaan untuk tabel `tb_pemesanan`
--
ALTER TABLE `tb_pemesanan`
  ADD CONSTRAINT `tb_pemesanan_ibfk_2` FOREIGN KEY (`id_katalog`) REFERENCES `tb_katalog` (`id_katalog`),
  ADD CONSTRAINT `tb_pemesanan_ibfk_3` FOREIGN KEY (`id_pelanggan`) REFERENCES `tb_user` (`id_pelanggan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
